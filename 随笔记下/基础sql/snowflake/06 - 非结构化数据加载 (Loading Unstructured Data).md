<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

### 06 - 非结构化数据加载 (Loading Unstructured Data)

Snowflake 通过 VARIANT 类型原生支持 JSON、Parquet 等半结构化/非结构化数据加载，整个流程为：创建 Stage → 加载原始数据到 VARIANT 表 → 解析分析 → 展平分层 → 插入最终结构化表。HR_data.json 示例包含嵌套 job 对象和 spoken_languages 数组。[^1][^2][^3]

#### 步骤1：准备 Stage 和 File Format

创建外部 Stage 指向 S3（如 bucketsnowflake-jsondemo），File Format 指定 TYPE=JSON：

```
CREATE OR REPLACE STAGE MANAGE_DB.EXTERNAL_STAGES.JSONSTAGE
    URL='s3://bucketsnowflake-jsondemo';
CREATE OR REPLACE FILE FORMAT MANAGE_DB.FILE_FORMATS.JSONFORMAT
    TYPE = JSON;
```

列出文件：`LIST @JSONSTAGE;` 确认 HR_data.json。[^1]

#### 步骤2：加载原始数据 (Raw Load)

创建 VARIANT 单列表，直接 COPY 整个 JSON 文件：

```
CREATE OR REPLACE TABLE OUR_FIRST_DB.PUBLIC.JSON_RAW (raw_file VARIANT);
COPY INTO JSON_RAW FROM @JSONSTAGE
    FILE_FORMAT=JSONFORMAT FILES=('HR_data.json');
SELECT * FROM JSON_RAW;  -- 显示完整 JSON 对象
```

VARIANT 自动解析，支持任意深度嵌套，无需预定义 Schema。[^2][^1]

#### 步骤3：解析与分析 (Parse \& Analyze)

使用路径语法提取字段，支持类型转换 `$1` 或 `RAW_FILE`：

```
-- 简单字段
SELECT RAW_FILE:first_name::STRING AS first_name,
       RAW_FILE:job.salary::INT AS salary
FROM JSON_RAW;

-- 嵌套对象
SELECT RAW_FILE:job.title::STRING AS job_title
FROM JSON_RAW;
```

验证数据：`SELECT RAW_FILE:city FROM JSON_RAW LIMIT 5;`。[^4]

#### 步骤4：处理嵌套与数组 (Nested \& Arrays)

**对象嵌套**：`RAW_FILE:job.salary::INT`。
**数组访问**：

- 索引：`RAW_FILE:prev_company[^0]::STRING`、`ARRAY_SIZE(RAW_FILE:prev_company)`。
- 示例：HR_data.json 中 spoken_languages 数组。[^5]

**手动展开（UNION ALL，小数据集）**：

```
SELECT id, first_name, spoken_languages[^0].language::STRING AS lang
FROM JSON_RAW
UNION ALL SELECT id, first_name, spoken_languages[^1].language::STRING
FROM JSON_RAW;
```


#### 步骤5：FLATTEN 展平分层数据 (核心：LATERAL FLATTEN)

高效处理数组/对象，用 `TABLE(FLATTEN())` 生成行：

```
SELECT RAW_FILE:id::INT AS id,
       RAW_FILE:first_name::STRING AS first_name,
       f.value:language::STRING AS language,
       f.value:level::STRING AS level
FROM JSON_RAW,
     LATERAL FLATTEN(INPUT => RAW_FILE:spoken_languages) f
ORDER BY id;
```

- `LATERAL`：允许引用外部表。
- `INPUT`：指定数组路径，`f.value` 访问展开元素。
- 处理多层：嵌套 FLATTEN，如 job 内数组。[^6]


#### 步骤6：创建最终结构化表并插入

设计规范化表（如 employees、languages、jobs），用 CTAS 或 INSERT：

```
CREATE TABLE employees AS
SELECT RAW_FILE:id::INT id, RAW_FILE:first_name::STRING first_name,
       RAW_FILE:job.title::STRING job_title, RAW_FILE:job.salary::INT salary
FROM JSON_RAW;

-- 语言表（FLATTEN）
CREATE TABLE employee_languages AS
SELECT RAW_FILE:id::INT employee_id, f.value:language::STRING language
FROM JSON_RAW, LATERAL FLATTEN(INPUT => RAW_FILE:spoken_languages) f;
```

最终 MERGE 或 INSERT 去重，确保星型模型。[^2]

#### Parquet 支持（Bonus）

类似流程，File Format `TYPE=PARQUET`，直接查询列名无需 VARIANT 展开：

```
COPY INTO parquet_raw FROM @stage (FILE_FORMAT => 'PARQUETFORMAT');
SELECT col1, col2 FROM parquet_raw;  -- Schema 自动推断
```

列式存储高效，适合大数据集。[^2]

**注意**：VARIANT 存储压缩高效但查询复杂数据需优化 Cluster Key；生产用 Streams + Tasks 增量处理。[attached_file:所有文件][^2]

<div align="center">⁂</div>

[^1]: 003-Create-Stage-Load-raw-JSON-1.txt

[^2]: All-course-slides.pdf

[^3]: 002-HR-data.json

[^4]: 004-Parse-Analyze-JSON-2.txt

[^5]: 005-Handling-nested-data-JSON-3.txt

[^6]: 006-Dealing-with-hierarchy-JSON-4.txt

