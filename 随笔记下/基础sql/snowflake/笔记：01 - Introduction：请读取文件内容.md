<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 笔记：01 - Introduction：请读取文件内容

这是课程的第一部分 **“01 - Introduction”** 的详细笔记，结合了你的幻灯片内容和我补充的“行话”解释：

### 1. 课程概览 (Getting Started)

这部分主要是热身，告诉你这个课程包含哪些模块。

* **核心模块**: Architecture (架构), Loading Data (加载数据), Performance (性能优化), Access Control (权限管理).
* **高级功能**: Time Travel (时光穿梭), Snowpipe (实时加载), Data Sharing (数据共享), Zero-Copy Cloning (零拷贝克隆).


### 2. Snowflake Architecture (Snowflake 架构 - 面试必考)

Snowflake 采用的是 **Shared-Disk** (共享磁盘) 和 **Shared-Nothing** (无共享) 的混合架构。

幻灯片第 8 页展示了最经典的三层架构：

* **Top Layer: Cloud Services (云服务层 - "Brain" 大脑)**
    * **功能**: 它是整个系统的总指挥。
    * **负责**: Authentication (认证), Access Control (权限), **Metadata Management (元数据管理)**, Query Parsing \& Optimization (SQL解析与优化)。
    * **关键点**: 这一层是 Serverless 的，你不需要管它，它决定了怎么分配任务。
* **Middle Layer: Query Processing (查询处理层 - "Muscle" 肌肉)**
    * **组件**: **Virtual Warehouses (虚拟数仓)**。
    * **本质**: 就是一组 EC2 虚拟机（计算节点）。
    * **MPP (Massively Parallel Processing)**: 海量并行处理。每个节点处理一部分数据。
    * **特点**: 存算分离。计算资源是独立的，可以秒级启动、销毁、扩容 (Scale Up/Out)。
* **Bottom Layer: Storage (存储层 - "Data" 数据)**
    * **位置**: 存储在云厂商的对象存储中 (AWS S3, Azure Blob, GCP Bucket)。
    * **格式**: **Hybrid Columnar (混合列式存储)**。
    * **Micro-partitions**: 数据被切分成微小的块（50-500MB），这是 Snowflake 性能的基石。


### 3. Virtual Warehouse Sizes (虚拟数仓规格)

* **T-Shirt Sizing**: XS (1节点), S (2节点), M (4节点) ... 4XL (128节点)。
* **计费单位**: **Credits (信用分)**。
    * XS = 1 Credit/hour
    * S = 2 Credits/hour
    * 每次翻倍。
* **自动挂起 (Auto-Suspend)**: 极其重要的省钱功能。闲置 X 分钟后自动关机。


### 4. Data Warehousing Concepts (数仓概念)

* **定义**: 用于报告 (Reporting) 和数据分析 (Analysis) 的数据库。
* **Layering (分层)**:
    * **Raw Data (原始层)**: 刚从源头拿来的脏数据。
    * **Staging Area (暂存层)**: 临时落脚点。
    * **Integration/Transformation (加工层)**: 清洗、Join。
    * **Access Layer (应用层)**: 给 BI 或 Data Science 用的干净数据。
* **ELT vs ETL**: Snowflake 推崇 **ELT** (Extract-Load-Transform)。先把数据一股脑 **Load** 进 Snowflake (因为它存算分离，存储便宜)，然后在数据库内部用强大的算力做 **Transform** (转化)。


### 5. Cloud Computing (云计算)

* **SaaS (Software as a Service)**: Snowflake 是 SaaS。
    * 你不需要管物理服务器、安装软件、打补丁。
    * 你只需要管：建表、写 SQL、管用户。
* **Cloud Agnostic**: Snowflake 可以跑在 AWS, Azure, GCP 上，对用户来说体验是一样的。


### 6. Snowflake Editions (版本)

这是考点，决定了你有多少高级功能。

* **Standard**: 入门级。有时光穿梭 (1天)。
* **Enterprise**: **企业级 (最常用)**。
    * 多了 **Multi-cluster warehouse** (自动并发扩展)。
    * 多了 **Time Travel up to 90 days** (90天时光穿梭)。
    * 多了 **Materialized Views** (物化视图)。
* **Business Critical**: 金融/医疗级。
    * 支持 HIPAA/PCI 合规。
    * **Database Failover** (跨区域灾备)。
* **Virtual Private**: 最高级。完全物理隔离的环境。


### 7. Pricing (计费模式)

* **Compute (计算)**: 按 **秒** 计费 (最少 1 分钟)。用多少算多少。
* **Storage (存储)**: 按压缩后的 TB/月 计费 (比如 \$23/TB/Month，很便宜，和 S3 差不多)。


### 8. Snowflake Roles (角色管理 - RBAC)

Snowflake 预置了几个系统角色，层级分明：

* **ACCOUNTADMIN**: **皇帝**。拥有最高权限，能看账单 (Billing)。尽量少用。
* **SECURITYADMIN**: **锦衣卫**。管用户 (Users) 和 角色 (Roles) 的创建与授权。
* **SYSADMIN**: **大管家**。拥有所有数据库对象 (Database, Warehouse, Table) 的所有权。平时建表、建库用这个角色。
* **USERADMIN**: 专门用来创建用户的低配版 SecurityAdmin。
* **PUBLIC**: 平民。所有人默认都有这个角色。

**最佳实践**:

* 不要直接给 User 赋权。
* 把权限赋给 Role。
* 把 Role 赋给 User。
* 自定义 Role (Custom Roles) 应该“挂”在 `SYSADMIN` 下面，这样大管家才能管理它们。
<span style="display:none">[^1]</span>

<div align="center">⁂</div>

[^1]: All-course-slides.pdf

