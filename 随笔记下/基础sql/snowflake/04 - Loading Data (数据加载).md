<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

### 04 - Loading Data (数据加载)

Snowflake 数据加载采用**Stage + COPY**模式，支持**Bulk Loading**（批量，大文件）和**Snowpipe**（连续，小文件 Serverless）。核心是先创建 Stage（临时区），再用 COPY 从 Stage 导入表，支持实时转换。[^1][^2]

#### Stage 类型与创建

- **External Stage**：指向云存储（S3/Azure/GCS），需凭证或公开访问。跨区域/平台有额外传输费。[^2][^1]

```
CREATE STAGE MANAGE_DB.external_stages.aws_stage 
URL='s3://bucketsnowflakes3' 
CREDENTIALS=(AWS_KEY_ID='xxx' AWS_SECRET_KEY='yyy');  -- 私密 [file:181]
-- 或无凭证（公开桶）
```

- **Internal Stage**：Snowflake 托管，无额外存储费。`LIST @stage;` 查看文件，`DESC STAGE stage_name;` 查看详情。[^2]
- **实战**：`LIST @MANAGE_DB.external_stages.aws_stage pattern='.*Order.*';` 匹配文件。[^3]


#### COPY 命令语法与文件选择

```
COPY INTO target_table 
FROM @stage_name 
FILE_FORMAT=(TYPE=csv FIELD_DELIMITER=',' SKIP_HEADER=1)  -- 内联格式 [file:180]
FILES=('file1.csv', 'file2.csv')  -- 指定文件
PATTERN='.*Order.*csv';  -- 正则匹配
```

- **File Format 对象**：复用定义 `CREATE FILE FORMAT my_csv TYPE='CSV' FIELD_DELIMITER=',';`。[^2]
- **列映射**：按序 `$1,$2,...` 或 SELECT 转换。[^3]


#### 数据转换（ELT 核心）

COPY 支持**内联 SELECT**，边加载边转换，减少下游 ETL：

```
COPY INTO orders 
FROM (SELECT $1::VARCHAR as order_id, 
      $2::INT as amount, 
      CASE WHEN $3::INT < 0 THEN '亏损' ELSE '盈利' END as flag 
      FROM @stage) 
FILE_FORMAT=...;[file:179]
```

- 支持：CAST、CASE、SUBSTRING、聚合等 SQL 函数。[^4]


#### 关键 COPY Options

| Option | 值 | 作用 |
| :-- | :-- | :-- |
| ON_ERROR | 'CONTINUE'/'SKIP_FILE'/'SKIP_FILE_N'/'ABORT_STATEMENT' | 错误处理：跳过坏行/文件，默认中止。[^5] |
| VALIDATION_MODE | 'RETURN_10_ROWS'/'RETURN_ERRORS' | 仅验证不加载，预览错误。[^2] |
| PURGE | TRUE | 成功后删除源文件。[^2] |
| FORCE | TRUE | 强制重载已处理文件。[^2] |
| SIZE_LIMIT | 1000000 | 字节限额，超则停。[^2] |
| TRUNCATECOLUMNS | TRUE | 超长字符串截断。[^2] [^5] |

**实战提示**：先用 `ON_ERROR='CONTINUE'` 加载脏数据，再清洗。监控 `COPY_HISTORY` 视图查加载状态和错误文件。[^5]

<div align="center">⁂</div>

[^1]: 003-Create-Stage.txt

[^2]: All-course-slides.pdf

[^3]: 004-COPY-Command.txt

[^4]: 005-Transforming-data.txt

[^5]: 007-Copy-options-ON-ERROR.txt

