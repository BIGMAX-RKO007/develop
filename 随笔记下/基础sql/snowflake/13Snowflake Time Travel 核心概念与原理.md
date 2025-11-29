<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

## Snowflake Time Travel 核心概念与原理

Snowflake Time Travel 允许用户查询和恢复表、Schema 或 Database 在过去特定时间点（Retention Period 内）的历史状态，支持 DML（如 UPDATE/DELETE）和 DDL（如 DROP）操作的回溯，默认 Retention Period 为 1 天，企业版可达 90 天。 它基于微分区元数据元数据存储历史变更，无需额外备份，查询历史数据时使用 AT/OFFSET、BEFORE TIMESTAMP 或 BEFORE STATEMENT 等语法，当前表数据不受影响。[^1][^2][^3]

## 查询历史数据三种方式

- **AT OFFSET -n**：回溯 n 分钟前数据，例如 `SELECT * FROM test AT (OFFSET => -60);`，适合最近变更。[^1]
- **BEFORE TIMESTAMP**：指定精确时间点，如 `SELECT * FROM test BEFORE (TIMESTAMP => '2021-04-15 17:47:50.581');`，需注意时区（建议 ALTER SESSION SET TIMEZONE = 'UTC'）。[^1]
- **BEFORE STATEMENT (Query ID)**：基于特定 SQL 语句前状态，Query ID 可从查询历史获取，如 `SELECT * FROM test BEFORE (STATEMENT => '019b9ee5-0500-8473-0043-4d8300073062');`，精确到单次操作。[^1]


## 数据恢复操作详解

恢复时避免直接 `CREATE OR REPLACE TABLE test AS SELECT * FROM test BEFORE (...)`，因会覆盖当前数据丢失最新变更；推荐创建备份表 `CREATE TABLE test_backup AS SELECT * FROM test BEFORE (...);`，然后 `TRUNCATE test; INSERT INTO test SELECT * FROM test_backup;`，这样安全合并历史与当前数据。 对于误 DELETE/UPDATE，可直接用历史查询结果覆盖或插入；模拟场景中，先 LOAD CSV 到 test 表，再模拟 UPDATE firstname='Joyen' 或 SET email=NULL，最后用 Time Travel 验证恢复。[^2][^1]

## UNDROP 命令应用场景

UNDROP 可恢复最近 DROP 的表/Schema/Database，只要在 Retention Period 内：`UNDROP TABLE ourfirstdb.public.customers;`，即使表名冲突，也能先 RENAME 当前表再 UNDROP（如 ALTER TABLE customers RENAME TO customers_wrong;）。 支持层级恢复：DROP SCHEMA → UNDROP SCHEMA，DROP DATABASE → UNDROP DATABASE，适用于 DDL 误操作；DROP 后原表不可见，直至 UNDROP。[^4]

## Retention Period 配置与成本分析

每个表可独立设置 `ALTER TABLE customers SET DATA_RETENTION_TIME_IN_DAYS = 3;`，范围 0~90 天（0 表示禁用 Time Travel），用 `SHOW TABLES LIKE 'CUSTOMERS';` 查看当前值。 存储成本：Time Travel 数据计入 TIME_TRAVEL_BYTES（从 SNOWFLAKE.ACCOUNT_USAGE.TABLE_STORAGE_METRICS 查询），总存储按活跃数据 + 历史数据收费；监控 `SELECT * FROM SNOWFLAKE.ACCOUNT_USAGE.STORAGE_USAGE ORDER BY USAGE_DATE DESC LIMIT 10;`，优化 Retention 以控制费用。 Enterprise 版支持更长 Retention，适合合规审计场景。[^5][^3]

<div align="center">⁂</div>

[^1]: 002-Using-time-travel.txt

[^2]: 003-Restoring-in-time-travel.txt

[^3]: 005-Retention-period.txt

[^4]: 004-Undrop.txt

[^5]: 006-Time-travel-cost.txt

