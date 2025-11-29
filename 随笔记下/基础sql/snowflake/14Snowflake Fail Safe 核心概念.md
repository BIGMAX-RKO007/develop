<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

## Snowflake Fail Safe 核心概念

Snowflake Fail Safe 是 Time Travel Retention Period（默认 1 天）过期后的额外 7 天数据恢复窗口，提供灾难恢复能力，防止永久数据丢失，仅 ACCOUNTADMIN 角色可联系 Snowflake 支持请求恢复，无法用户自助查询或操作。 它覆盖 DML/DDL 变更的微分区历史，即使表被 DROP 超过 Retention 时间，仍可恢复到 Fail Safe 期起点。[^1]

## 与 Time Travel 的关系与时序

- Time Travel：0~Retention 天（可配置 0-90 天），用户可查询/恢复。
- Fail Safe：紧接 Retention 后固定 7 天，Snowflake 内部维护，用户不可见/查询，仅用于极端情况如误操作覆盖所有历史。
- Retention 结束后，Time Travel 数据移入 Fail Safe，TOTAL STORAGE = ACTIVE + TIME_TRAVEL + FAILSAFE。[^1]


## 访问与恢复流程

Fail Safe 数据不可直接查询（如无 AT/OFFSET 支持），需 ACCOUNTADMIN 通过 Snowflake 支持门户提交工单，提供表名、恢复时间点，Snowflake 工程师手动恢复到新表（可能收费）。 不支持 UNDROP，适用于合规审计或黑客攻击场景；恢复后，原表不变，新表命名如 _RESTORED。[^1]

## 存储使用监控

在 ACCOUNT_USAGE 视图监控 Fail Safe 占用：

- 账户级：`SELECT USAGE_DATE, STORAGE_BYTES/POW(1024,3) AS STORAGE_GB, FAILSAFE_BYTES/POW(1024,3) AS FAILSAFE_GB FROM SNOWFLAKE.ACCOUNT_USAGE.STORAGE_USAGE ORDER BY USAGE_DATE DESC;` 显示每日总 FAILSAFE_GB。[^1]
- 表级：`SELECT TABLE_NAME, ACTIVE_BYTES/POW(1024,3) AS ACTIVE_GB, FAILSAFE_BYTES/POW(1024,3) AS FAILSAFE_GB FROM SNOWFLAKE.ACCOUNT_USAGE.TABLE_STORAGE_METRICS ORDER BY FAILSAFE_GB DESC;` 识别高 Fail Safe 表，优化 Retention 配置降低成本。[^1]
Fail Safe 存储按标准费率计费，无法删除，Retention=0 时 Time Travel 立即进 Fail Safe。[^1]

<div align="center">⁂</div>

[^1]: 002-Fail-Safe-Storage.txt

