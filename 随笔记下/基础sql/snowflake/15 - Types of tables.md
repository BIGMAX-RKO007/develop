<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 15 - Types of tables

Snowflake 里核心有三类表：Permanent、Transient 和 Temporary，它们的主要差异在于 Time Travel / Fail Safe、生命周期和成本保护级别。 选型时本质是在“恢复能力 vs 成本/临时性”之间做权衡。[^1][^2][^3]

## 三种表类型概览

- Permanent table：长期业务数据，支持 Time Travel + Fail Safe，恢复能力最强，成本最高。[^1]
- Transient table：取消 Fail Safe，只保留（可选的）Time Travel，用于中间结果或不需要长期灾备的数据，成本更低。[^3]
- Temporary table：会话级表，只在当前会话内可见，用完即丢，一般用于临时计算、ETL 中间步骤。[^2]


### 对比一览表

| 维度 | Permanent | Transient | Temporary |
| :-- | :-- | :-- | :-- |
| 典型用途 | 生产业务事实/维度表，长期保留数据。[^1] | 中间结果、预计算表、可再生成数据。[^3] | ETL 临时计算、复杂查询的中间表。[^2] |
| 创建示例 | `CREATE OR REPLACE TABLE db.schema.customers (...);`[^1] | `CREATE OR REPLACE TRANSIENT TABLE db.schema.customerstransient (...);`[^3] | `CREATE OR REPLACE TEMPORARY TABLE db.schema.temptable (...);`[^2] |
| Time Travel | 支持，默认保留期（如 1 天，可配置 0–90）。[^1] | 支持，但常配合将 `DATA_RETENTION_TIME_IN_DAYS` 设小或设为 0 降低成本。[^3] | 支持会话内 Time Travel 查询，但表随会话结束自动删除。[^2] |
| Fail Safe | 有 Fail Safe 字节统计，用于灾难恢复。[^1] | 无 Fail Safe（只看到 FAILSAFEBYTES=0），删除后无法通过 Fail Safe 恢复。[^3] | 无长期 Fail Safe，主要依赖短期会话范围和 Time Travel。[^2] |
| 生命周期 | 显式 DROP 前一直存在，可 UNDROP（在 retention 内）。[^1] | 显式 DROP，且 retention 可能为 0，适合“可重算数据”。[^3] | 随会话自动 DROP，同名 permanent 只在 temp 删除后才重新可见。[^2] |

## Permanent tables 要点

示例课程中通过 `CREATE OR REPLACE DATABASE PDB` 与 `CREATE OR REPLACE TABLE PDB.PUBLIC.CUSTOMERS (...)` 创建永久表，并从外部 stage COPY 大量数据，随后用 ACCOUNT_USAGE.TABLE_STORAGE_METRICS 查看 ACTIVE/TIMETRAVEL/FAILSAFE 字节和 ISTRANSIENT 标志，确认这是 permanent 表。 此类表适合核心业务数据，因为支持 Time Travel 查询历史版本，以及在极端场景下通过 Fail Safe 做灾备恢复。[^1]

## Transient tables 要点

示例中使用 `CREATE OR REPLACE TRANSIENT TABLE TDB.PUBLIC.CUSTOMERSTRANSIENT (...)`，然后从 OURFIRSTDB.PUBLIC.CUSTOMERS 交叉 JOIN 装载大量数据，再用 TABLE_STORAGE_METRICS 查询 ISTRANSIENT、TIMETRAVELBYTES 和 FAILSAFEBYTES 等字段，可见 Fail Safe 字节为 0。 演示还通过 `ALTER TABLE ... SET DATA_RETENTION_TIME_IN_DAYS = 0` 关闭 Time Travel，DROP/UNDROP 表验证仅在短期内可恢复，因此非常适合作为可再生成的中间数据存储，降低存储和 Fail Safe 成本。[^3]

## Temporary tables 要点

示例脚本先在 PDB 中创建 permanent 表 `PDB.PUBLIC.CUSTOMERS` 并插入数据，然后再创建同名的 `CREATE OR REPLACE TEMPORARY TABLE PDB.PUBLIC.CUSTOMERS (...)`，此时 `SELECT * FROM PDB.PUBLIC.CUSTOMERS` 返回的是临时表，说明 temp 表在名称解析上优先于同名 permanent 表。 随后又创建 `PDB.PUBLIC.TEMPTABLE` 并把数据插入其中，用于纯会话内分析；SHOW TABLES 能看到这些 temp 表，只在当前会话存在，关闭会话后 Snowflake 自动删除，不进长期 Fail Safe，因此非常适合一次性分析或复杂 SQL 拆分的中间结果。[^2]

<div align="center">⁂</div>

[^1]: 002-Permanent-tables.txt

[^2]: 004-Temporary-tables.txt

[^3]: 003-Transient-tables.txt

