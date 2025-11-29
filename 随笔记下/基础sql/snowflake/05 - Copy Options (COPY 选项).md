<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

### 05 - Copy Options (COPY 选项)

Snowflake COPY 命令提供丰富选项控制加载行为，包括错误处理、验证、文件过滤等。默认严格（ON_ERROR=ABORT_STATEMENT），需显式配置容错。[^1][^2]

#### 错误处理 (ON_ERROR)

控制加载失败行为，适用于脏数据场景：


| 值 | 行为 | 示例 |
| :-- | :-- | :-- |
| 'CONTINUE' (默认 ABORT_STATEMENT) | 跳过坏行，继续加载 | `ON_ERROR='CONTINUE'` 加载 1000 行中 990 行 [^2] |
| 'SKIP_FILE' | 跳过整个坏文件 | 处理 OrderDetails_error.csv [^2] |
| 'SKIP_FILE_N' | 最多跳过 N 个坏文件 | `ON_ERROR='SKIP_FILE_3'` [^2] |
| 'SKIP_FILE_%' | 模式匹配跳过 | `ON_ERROR='SKIP_FILE_3%'` [^2] |

**实战**：`COPY INTO table FROM @stage ON_ERROR='CONTINUE';` 先粗加载，再清洗。[^2]

#### 验证模式 (VALIDATION_MODE)

不加载，仅检查数据：

- `RETURN_ERRORS`：列出所有错误，无行返回（若无错则空）。
- `RETURN_N_ROWS`：返回前 N 行（若出错则报错），如 `RETURN_10_ROWS`。[^3]

```
COPY INTO orders FROM @stage VALIDATION_MODE='RETURN_ERRORS';
```

先验证再正式加载，避免无效计算。[^3]

#### 输出过滤与转换

- `RETURN_FAILED_ONLY=TRUE`：仅显示有错误的失败文件（结合 ON_ERROR=CONTINUE），默认 FALSE。[^4]
- `TRUNCATECOLUMNS=TRUE`：超长 VARCHAR 自动截断（如 VARCHAR(10) 截 "Electronics" 为 "Electronic"），默认 FALSE。[^5]
- `FORCE=TRUE`：强制重载已处理文件，可能重复数据。[^1]


#### 其他限制选项

- `SIZE_LIMIT=字节数`：单命令最大加载大小，超则停止（如 30 字节测试）。[^1]
- `PURGE=TRUE`：成功后删除源文件（仅内部/有权限外部 Stage）。[^1]

**监控**：`SELECT * FROM TABLE(INFORMATION_SCHEMA.COPY_HISTORY(TABLE_NAME=>'ORDERS_EX', START_TIME=>DATEADD(HOURS, -1, CURRENT_TIMESTAMP())));` 查看加载历史、错误文件。[^1]

<div align="center">⁂</div>

[^1]: All-course-slides.pdf

[^2]: 007-Copy-options-ON-ERROR.txt

[^3]: 002-VALIDATION_MODE_en.srt

[^4]: 005-RETURN_FAILED_ONLY_en.srt

[^5]: 006-TRUNCATECOLUMNS_en.srt

