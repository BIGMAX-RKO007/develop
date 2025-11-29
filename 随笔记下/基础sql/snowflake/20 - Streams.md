<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 20 - Streams

Snowflake Streams 是变更数据捕获（CDC）对象，实时跟踪表上的 INSERT、UPDATE、DELETE 操作，提供元数据如变更类型、行标识和旧新值，便于数据复制或增量处理。[attached_file:8198e320-b510-442f-bd7e-233fe46f7cdd][^1]

## Streams 创建与查询

创建 Streams：`CREATE STREAM stream_name ON TABLE source_table`，支持 APPEND_ONLY 模式（仅新增）或标准模式（全 DML）。查询变更使用 `SELECT * FROM stream_name WHERE METADATA$ACTION = 'INSERT'`，包含 METADATA$ISUPDATE、METADATA$ROW_ID 等列，`DESC STREAM` 查看偏移。[^1]

## 变更消费与 CHANGETRACKING

消费 Streams（如 INSERT INTO target SELECT FROM stream）会自动推进读偏移，避免重复。表级 `ALTER TABLE xxx SET CHANGE_TRACKING = TRUE` 启用后，可用 `SELECT FROM table CHANGES AT (TIMESTAMP => ...)` 查询历史变更，支持 append-only 过滤。[^2]

## 实际示例场景

- 源表插入数据后，Streams 捕获新行，JOIN 其他表后消费到目标表，实现 ETL。
- UPDATE 操作在 Streams 中标记 ISUPDATE=1，提供旧值元数据，便于 MERGE 处理。
Streams 常与 Tasks 结合自动化，或 Pipes 实现流式加载。[attached_file:6ac24921-f2f8-4bf7-9a1f-46fe7f26f90e][attached_file:136bf638-0e9c-445e-927d-877b20a2be95]

<div align="center">⁂</div>

[^1]: 002-Insert.txt

[^2]: 011-Change-clause.txt

