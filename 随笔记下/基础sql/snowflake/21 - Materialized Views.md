<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 21 - Materialized Views

Snowflake Materialized Views（物化视图）通过预计算和存储查询结果来加速频繁执行的复杂查询，尤其适用于聚合或预处理大数据集，避免每次查询重复计算。[attached_file:e539be0a-891d-4868-9b5c-4a9159823dfd]

## 创建与查询

创建物化视图语法为 `CREATE MATERIALIZED VIEW mv_name AS SELECT ...`。虽然查询语法与普通表相同，但在后台 Snowflake 会自动利用物化视图的结果。当底层基表（Base Table）数据发生 INSERT、UPDATE 或 DELETE 时，Snowflake 的后台服务会自动维护并更新物化视图，确保数据一致性。[^1][^2]

## 自动维护与成本

物化视图的刷新是由 Snowflake 自动管理的，不使用用户创建的 Virtual Warehouse，而是消耗 **Serverless Compute** 资源（Credits）。你可以通过 `SHOW MATERIALIZED VIEWS` 查看视图状态，或查询 `INFORMATION_SCHEMA.MATERIALIZED_VIEW_REFRESH_HISTORY` 监控刷新历史和信用消耗。[^3][^1]

## 使用场景与限制

- **适用**：查询频繁但基表数据变更不频繁的场景，如仪表板聚合数据。
- **不适用**：基表数据频繁变更（会导致高维护成本）或查询很少执行的场景。
- **限制**：支持聚合、过滤等，但对 JOIN 操作有限制（通常只能引用单表或特定类型的 JOIN）。[attached_file:e539be0a-891d-4868-9b5c-4a9159823dfd][attached_file:c7fe0780-7193-4e84-a0a7-7c462a428761]

<div align="center">⁂</div>

[^1]: 003-Refresh-in-materialized-views.txt

[^2]: 002-Create-materialized-view.txt

[^3]: 004-Maintenance-costs.txt

