<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 24 - Visualization - Power BI \& Tableau

Snowflake Partner Connect 和 Marketplace 是 Snowflake 生态系统中的两个关键组件，用于简化与第三方工具的集成和数据共享。[attached_file:03ac8161-f0f2-4ba1-b3a9-6ca51f76f0f7][attached_file:5fe45116-d679-410d-bc54-f38560624845]

## Partner Connect

Partner Connect 允许用户通过 Snowflake 界面一键试用和连接精选的第三方 ISV（独立软件供应商）工具，如 Fivetran、dbt、Informatica 等。

* **工作原理**：当你在 Partner Connect 中点击某个合作伙伴磁贴时，Snowflake 会自动创建一个专用的数据库、仓库、用户和角色，并将这些凭据发送给合作伙伴，从而为你快速创建一个试用账户并建立连接。
* **优势**：极大地简化了手动配置连接的繁琐步骤，适合快速评估和启动新工具。只有 `ACCOUNTADMIN` 角色可以启动此流程。[attached_file:03ac8161-f0f2-4ba1-b3a9-6ca51f76f0f7]


## Snowflake Marketplace

Snowflake Marketplace 是一个数据应用商店，允许数据消费者发现和访问第三方提供的数据集和服务。

* **即时访问**：与传统的数据传输（如 FTP、API）不同，Marketplace 中的数据通过 Secure Data Sharing 技术提供。这意味着数据不是被复制到你的账户，而是作为只读数据库直接挂载，你可以立即查询，数据始终保持最新。
* **数据类型**：包含公共数据集（如天气、人口统计）、SaaS 数据连接器以及特定行业的付费数据集。
* **使用流程**：在 Snowsight 界面进入 Marketplace，搜索所需数据，点击 "Get" 即可在你的账户中创建一个数据库引用。[attached_file:5fe45116-d679-410d-bc54-f38560624845]

