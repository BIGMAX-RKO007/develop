<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

## 从 GCP Cloud Storage 加载数据概述

Snowflake 通过存储集成安全连接 Google Cloud Storage (GCS)，使用外部阶段从存储桶加载 CSV/JSON 数据。主要步骤包括创建 GCP 存储桶、上传文件、在 Snowflake 创建 GCS 存储集成、设置阶段和文件格式，最后使用 COPY 命令加载数据。[^1][^2][^3]

## GCP 存储桶设置

在 GCP Console 创建存储桶（如 snowflakebucketgcp、snowflakebucketgcpjson），上传数据文件如 world-happiness-report-2021.csv（包含国家幸福度指标）和 CarModels.json（汽车所有者数据）。确保存储桶路径格式为 gcs://bucketname/，并设置适当权限。[^4][^5]

## Snowflake 存储集成创建

使用 ACCOUNTADMIN 角色，在 DEMODB 中执行：

```
CREATE STORAGE INTEGRATION gcpintegration
  TYPE = EXTERNAL_STAGE
  STORAGE_PROVIDER = 'GCS'
  ENABLED = TRUE
  STORAGE_ALLOWED_LOCATIONS = ('gcs://snowflakebucketgcp/', 'gcs://snowflakebucketgcpjson/');
```

运行 `DESC STORAGE INTEGRATION gcpintegration;` 查看集成详情，在 GCP 授予 Snowflake 服务账户相应角色（如 Storage Object Viewer）。[^3][^1]

## 阶段和文件格式配置

创建 CSV 文件格式：

```
CREATE OR REPLACE FILE FORMAT demodb.public.fileformatgcp
  TYPE = 'CSV'
  FIELD_DELIMITER = ','
  SKIP_HEADER = 1;
```

创建阶段：

```
CREATE OR REPLACE STAGE demodb.public.stagegcp
  STORAGE_INTEGRATION = gcpintegration
  URL = 'gcs://snowflakebucketgcp/csv/happiness/'
  FILE_FORMAT = fileformatgcp;
```

验证连接：`LIST @demodb.public.stagegcp;`。查询阶段数据：`SELECT $1,$2,... FROM @stagegcp;`。[^6][^2]

## 数据加载执行

创建目标表（如 happiness，匹配 CSV 列如 country_name、ladder_score 等）：

```
CREATE OR REPLACE TABLE happiness (
  country_name VARCHAR,
  regional_indicator VARCHAR,
  ladder_score NUMBER(4,3),
  -- ... 其他19列
);
```

加载数据：`COPY INTO happiness FROM @demodb.public.stagegcp;`。验证：`SELECT * FROM happiness;`。对于 JSON，使用 TYPE='JSON' 并解析 \$1:"field"::STRING。[^6][^1]

<div align="center">⁂</div>

[^1]: 006-Unload-data.txt

[^2]: 004-Create-stage.txt

[^3]: 003-Create-integration-object.txt

[^4]: 002-CarModels.json

[^5]: 002-world-happiness-report-2021.csv

[^6]: 005-Query-Load-data.txt

