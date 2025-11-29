<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

### 07 - 性能优化 (Performance Optimization)

Snowflake 性能优化核心为：专用虚拟仓库隔离工作负载、Scaling Up/Down 处理查询复杂度、Scaling Out 处理并发用户、最大化缓存利用、Clustering Key 优化大表微分区扫描。目标是查询加速 + 成本控制，按查询计划分析全表扫描。[^1][^2][^3]

#### 专用虚拟仓库 (Dedicated VW)

为不同工作负载创建隔离仓库，避免资源竞争：

```
CREATE WAREHOUSE DS_WH WITH WAREHOUSE_SIZE='SMALL' 
MIN_CLUSTER_COUNT=1 MAX_CLUSTER_COUNT=1 AUTO_SUSPEND=300;
CREATE ROLE DATA_SCIENTIST; GRANT USAGE ON WAREHOUSE DS_WH TO ROLE DATA_SCIENTIST;
```

- ETL/ELT 用大仓库，BI/Reporting 用小仓库，Data Science 专用。
- 绑定角色+用户：`CREATE USER DS1 DEFAULT_ROLE='DATA_SCIENTIST' DEFAULT_WAREHOUSE='DS_WH';`。
避免过多仓库导致利用率低，定期审视工作模式。[^4][^3]


#### Scaling Up/Down (垂直扩展)

调整仓库大小应对查询复杂度变化：

```
ALTER WAREHOUSE COMPUTE_WH SET WAREHOUSE_SIZE = 'SMALL';  -- 从 XS 升到 S
```

- **Up**：复杂查询/ETL 峰值（如晚间批量）。
- **Down**：简单查询节省信用。
- 适用于已知负载模式，非并发用户（用 Scaling Out）。仓库大小：XS(1信用)-6XL(512信用)，按秒计费。[^2][^3]


#### Scaling Out (水平扩展，企业版+)

多集群仓库自动扩展处理并发：

```
CREATE WAREHOUSE MULTI_WH WITH MIN_CLUSTER_COUNT=1 MAX_CLUSTER_COUNT=10 
SCALING_POLICY='STANDARD';  -- 或 'ECONOMY' 保守模式
```

- **Standard**：队列或负载高时立即启动集群。
- **Economy**：预计负载持续6min才启动，节省信用。
- 最佳实践：企业版默认多集群，Max设高（成本相等，因速度翻倍）。模拟并发：多Worksheet 运行复杂查询观察自动扩展。[^5][^3]


#### 缓存最大化 (Maximize Caching)

三层自动缓存（24h有效，直至数据变更）：

- **Local Disk**：同一仓库重复查询最快。
- **Metadata/Result Cache**：跨仓库复用结果。
确保相似查询用同一仓库，如 Data Scientist 团队共享 DS_WH。示例：`SELECT AVG(C_BIRTH_YEAR) FROM TPCDS_SF100TCL.CUSTOMER;` 第二次瞬返。[^6][^3]


#### Clustering Key (大表优化)

手动优化微分区排序，减少扫描：

```
CREATE TABLE ORDERS_CACHING CLUSTER BY (DATE);
ALTER TABLE ORDERS_CACHING CLUSTER BY (MONTH(DATE));  -- 函数列
```

- 前：`SELECT * FROM ORDERS_CACHING WHERE DATE='2020-06-09';` 全扫。
- 后：仅扫描相关分区，性能提升。
适用于大表高选择性列（如日期、ID），Snowflake 自动维护但需自定义。[^3][^1]

**监控**：Query History 检查分区扫描数，结果缓存命中；Resource Monitors 限额。[attached_file:性能文件][^3]

<div align="center">⁂</div>

[^1]: 009-Clustering.txt

[^2]: 004-Scaling-up_en.srt

[^3]: All-course-slides.pdf

[^4]: 003-Dedicated-VW.txt

[^5]: 005-Scaling-out_en.srt

[^6]: 007-Caching.txt

