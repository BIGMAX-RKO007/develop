<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

## 从 AWS S3 加载数据概述

Snowflake 通过存储集成（Storage Integration）安全连接 AWS S3，实现从外部阶段加载数据。主要步骤包括在 AWS IAM 创建角色、在 Snowflake 创建存储集成对象、设置外部阶段和文件格式，最后使用 COPY 命令加载。[^1][^2][^3]

## AWS IAM 角色设置

在 AWS IAM 服务中创建角色，选择“另一个 AWS 账户”，输入 Snowflake 账户 ID（从 Snowflake 安全凭据获取），添加临时凭据选项，并附带外部 ID（临时值为 00000）。为角色附加 S3 全访问策略，并记录角色 ARN，用于后续 Snowflake 配置。[^4]

## Snowflake 存储集成创建

使用 ACCOUNTADMIN 角色执行以下 SQL 创建存储集成：

```
CREATE OR REPLACE STORAGE INTEGRATION s3_int
  TYPE = EXTERNAL_STAGE
  STORAGE_PROVIDER = 'S3'
  ENABLED = TRUE
  STORAGE_AWS_ROLE_ARN = 'arn:aws:iam::YOUR_AWS_ACCOUNT:role/YOUR_ROLE_NAME'
  STORAGE_ALLOWED_LOCATIONS = ('s3://your-bucket-name/csv/', 's3://your-bucket-name/json/');
```

运行 `DESC INTEGRATION s3_int;` 获取 Snowflake 生成的外部 ID 和用户 ARN，返回 AWS 更新信任关系 JSON 策略。[^3][^5]

## 阶段和文件格式配置

创建 CSV 文件格式：

```
CREATE OR REPLACE FILE FORMAT MANAGE_DB.FILE_FORMATS.csv_fileformat
  TYPE = 'CSV'
  FIELD_DELIMITER = ','
  SKIP_HEADER = 1
  NULL_IF = ('NULL', 'null')
  EMPTY_FIELD_AS_NULL = TRUE
  FIELD_OPTIONALLY_ENCLOSED_BY = '"';
```

创建外部阶段：

```
CREATE OR REPLACE STAGE MANAGE_DB.EXTERNAL_STAGES.csv_folder
  URL = 's3://your-bucket-name/csv/'
  STORAGE_INTEGRATION = s3_int
  FILE_FORMAT = MANAGE_DB.FILE_FORMATS.csv_fileformat;
```

创建目标表（如 movie_titles，列匹配 CSV），然后执行 `COPY INTO your_table FROM @csv_folder;` 加载数据。[^6][^2]

<div align="center">⁂</div>

[^1]: All-course-slides.pdf

[^2]: 006-Load-data-from-S3.txt

[^3]: 005-Create-Storage-integration.txt

[^4]: 004-Creating-policy_en.srt

[^5]: 005-Creating-integration-object_en.srt

[^6]: 006-Loading-from-S3_en.srt

