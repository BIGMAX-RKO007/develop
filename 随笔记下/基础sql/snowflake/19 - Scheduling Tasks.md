<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 19 - Scheduling Tasks

Snowflake Tasks 是内置的调度机制，用于自动化执行 SQL 语句、存储过程或调用子任务，支持定时（间隔或 CRON）运行，适合 ETL、日结报表或数据清理等场景。[^1][^2]

## 任务创建基础

创建任务需指定仓库、SQL 语句和调度器，例如间隔调度 `SCHEDULE '1 MINUTE'` 或 CRON 表达式 `SCHEDULE 'USING CRON 0 6 * * * UTC'`（每天 UTC 6:00 执行）。任务默认挂起，使用 `ALTER TASK xxx RESUME` 启动、`SUSPEND` 暂停，并通过 `SHOW TASKS` 查看列表。[^2][^3]

## 高级用法

任务支持条件执行 `WHEN dition>`、调用存储过程 `AS CALL proc_name()`，以及树状依赖 `AFTER <predecessor_task>` 构建 DAG 工作流。示例中通过 JavaScript 存储过程插入数据，并每分钟调度执行。[^1]

## 历史与监控

使用 `SELECT * FROM TABLE(INFORMATION_SCHEMA.TASK_HISTORY())` 查询任务执行历史，支持过滤任务名或时间范围，查看状态、错误和持续时间，便于调试和错误处理。[^4]

<div align="center">⁂</div>

[^1]: 006-Task-with-stored-procedure.txt

[^2]: 002-Creating-Tasks.txt

[^3]: 003-Using-CRON.txt

[^4]: 007-Task-history.txt

