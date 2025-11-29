<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

### 03 - Snowflake Architecture (Snowflake 架构)

Snowflake 采用独特的**三层架构**（Cloud Services、Query Processing、Storage），实现**完全存算分离**，这是其核心优势。[^1][^2]

#### 三层架构详解

- **Cloud Services (云服务层 - "大脑")**：全局共享层，负责认证、权限控制、查询优化、元数据管理等。不消耗计算资源，全 Serverless。[^2]
- **Query Processing (查询处理层 - "肌肉")**：由**Virtual Warehouses (虚拟仓库)**组成，每个仓库是一组独立计算集群，支持 MPP (海量并行处理)。仓库间互不干扰，可独立缩放。[^2]
- **Storage (存储层)**：数据以**微分区 (Micro-partitions, 50-500MB)**形式存储在云对象存储 (S3/Blob/Bucket)，采用**混合列式格式**自动压缩。[^2]


#### Virtual Warehouse 规格与多集群

| 规格 | 节点数 | Credits/小时 (Standard Edition) |
| :-- | :-- | :-- |
| XS | 1 | 1 [^2] |
| S | 2 | 2 |
| M | 4 | 4 |
| L | 8 | 8 |
| XL | 16 | 16 |
| 2XL | 32 | 32 |
| 3XL | 64 | 64 |
| 4XL | 128 | 128 [^2] |

**Multi-Clustering (Enterprise+)**：自动扩展集群处理并发查询。

- **Standard Policy**：查询排队时立即启动新集群，优先性能。
- **Economy Policy**：预计负载>6分钟才启动，优先省 Credits，可能有队列。[^2]


#### 架构优势（为什么牛）

- **弹性缩放**：Scale Up (加大仓库规格，复杂查询) vs Scale Out (多仓库，并发用户)。[^2]
- **SaaS 模式**：用户只管 SQL，云厂商管物理存储，Snowflake 管 OS/元数据，用户零运维。[^1]
- **数仓分层适配**：Raw → Staging → Integration → Access，支持 ELT (先 Load 后 Transform，存储廉价)。[^3]

**实战提示**：创建仓库 `CREATE WAREHOUSE my_wh WITH WAREHOUSE_SIZE='XS' AUTO_SUSPEND=300;`。观察 Query Profile 看分区剪枝效果。[^2]
<span style="display:none">[^4]</span>

<div align="center">⁂</div>

[^1]: 002-Cloud-computing_en.srt

[^2]: All-course-slides.pdf

[^3]: 001-What-is-a-data-warehouse_en.srt

[^4]: 005-Data-Storage-Transfer-Cost_en.srt

