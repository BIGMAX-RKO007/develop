<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

## 从 Azure Blob 加载数据概述

Snowflake 通过存储集成安全连接 Azure Blob Storage，使用外部阶段从容器加载 CSV/JSON 数据。主要步骤包括创建 Azure 存储账户和容器、上传文件、在 Snowflake 创建存储集成对象、设置阶段和文件格式，最后使用 COPY 命令加载。[^1][^2][^3]

## Azure 账户和容器设置

创建免费 Azure 账户（提供 \$200 信用），然后创建存储账户（名称全局唯一，选择与 Snowflake 相同区域）。在存储账户下创建容器（如 snowflakecsv、snowflakejson），上传 CSV（如 world-happiness-report-2021.csv）和 JSON（如 CarModels.json）文件，确保容器私有。[^4][^5][^6][^7][^8]

## Snowflake 存储集成创建

使用 ACCOUNTADMIN 角色，在 DEMO_DB 中执行：

```
CREATE OR REPLACE STORAGE INTEGRATION azure_integration
  TYPE = EXTERNAL_STAGE
  STORAGE_PROVIDER = 'AZURE'
  ENABLED = TRUE
  AZURE_TENANT_ID = 'your-tenant-id'  -- 从 Azure "Tenant properties" 获取
  STORAGE_ALLOWED_LOCATIONS = ('azure://youraccount.blob.core.windows.net/snowflakecsv/', 'azure://youraccount.blob.core.windows.net/snowflakejson/');
```

运行 `DESC INTEGRATION azure_integration;` 获取访问信息，在 Azure 分配存储 Blob 数据贡献者角色给 Snowflake 服务主体。[^3][^9]

## 阶段和文件格式配置

创建 CSV 文件格式：

```
CREATE OR REPLACE FILE FORMAT demo_db.public.fileformat_azure
  TYPE = 'CSV'
  FIELD_DELIMITER = ','
  SKIP_HEADER = 1;
```

创建阶段：

```
CREATE OR REPLACE STAGE demo_db.public.stage_azure
  URL = 'azure://youraccount.blob.core.windows.net/snowflakecsv/'
  STORAGE_INTEGRATION = azure_integration
  FILE_FORMAT = fileformat_azure;
```

验证连接：`LIST @demo_db.public.stage_azure;`。创建表（如 happiness，列匹配 CSV），执行 `COPY INTO happiness FROM @stage_azure;` 加载。对于 JSON，使用 TYPE='JSON' 并查询 \$1:"field"::STRING。[^2][^10][^11][^1]
<span style="display:none">[^12]</span>

<div align="center">⁂</div>

[^1]: 005-Create-stage-test-connection_en.srt

[^2]: 005-Create-Stage.txt

[^3]: 004-Create-Integration.txt

[^4]: 001-Sign-up-for-free-trial_en.srt

[^5]: 003-CarModels.json

[^6]: 003-world-happiness-report-2021.csv

[^7]: 002-Create-a-storage-account_en.srt

[^8]: 003-Create-a-container_en.srt

[^9]: 004-Create-integration-object_en.srt

[^10]: 007-Load-JSON.txt

[^11]: 006-Load-CSV.txt

[^12]: 006-Load-CSV-file_en.srt

