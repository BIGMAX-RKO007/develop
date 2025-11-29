<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 17 - Data Sharing

Snowflake Data Sharing（Secure Data Sharing）允许账户间实时、安全共享数据库、schema、表或视图，而不复制数据，利用零拷贝元数据指针，仅共享者存储付费。[attached_file:52e88e41-52b6-45ce-9fad-0c88720e8c87] 消费者创建共享数据库后可查询最新数据，支持跨区域/跨云，完美用于数据市场、合作伙伴协作。[attached_file:52e88e41-52b6-45ce-9fad-0c88720e8c87]

## 共享创建与使用流程

创建分享：`CREATE SHARE myshare; GRANT USAGE ON DATABASE mydb TO SHARE myshare; ALTER SHARE myshare ADD DATABASE=myprod;` 或直接分享整个DB/schema。[attached_file:edf71fa1-c405-48ca-b284-96886041c87f] 通过 UI 或 SQL 获取 share link/email 外部账户；消费者用 `CREATE DATABASE consumerdb FROM SHARE provider.acct.myshare;` 创建本地数据库视图。[attached_file:d1861da2-c214-4a46-b228-46884e01a297] 支持动态添加/移除对象，变更实时同步，无需重新加载。[attached_file:edf71fa1-c405-48ca-b284-96886041c87f]

## Secure Views 安全共享

| 维度 | Normal View | Secure View |
| :-- | :-- | :-- |
| 创建 | `CREATE VIEW ... AS SELECT ...;` | `CREATE SECURE VIEW ... AS SELECT ...;`[attached_file:1ade5d1b-6835-4118-b003-a7cac2dcae71] |
| 共享者可见性 | 消费者可见底层表，可绕过视图直接查源表。[attached_file:1ade5d1b-6835-4118-b003-a7cac2dcae71] | 底层表对消费者隐藏，只能通过视图访问，实现行/列级过滤（如 CURRENT_USER() 权限控制）。[attached_file:54048bce-d29e-4f15-8c4e-defbebd6ec6b] |
| 适用场景 | 简单聚合共享 | 敏感数据脱敏/合规共享。[attached_file:1ade5d1b-6835-4118-b003-a7cac2dcae71] |

Secure View 示例：`CREATE SECURE VIEW sales_secure AS SELECT region, SUM(sales) FROM sales WHERE user_has_access();`，共享时防止基表暴露。[attached_file:d2f545e9-a534-4d7b-88dd-8a221d7e8182]

## Reader Account 与非 Snowflake 用户

Provider 创建 Reader Account：`CREATE ACCOUNT reader_acct ... TYPE = READER;`，消费者免费查询共享（Provider 承担计算费）。[attached_file:a3e95a98-fdb6-4991-b0dc-bb59dc264c68] 非 Snowflake 用户通过 AWS/GCP/Azure Marketplace 或 PrivateLink 接入，直接创建共享 DB，无需 Snowflake 账户。[attached_file:e10fb452-afbe-4b13-9698-1859cef53d55] 多 DB 共享示例：一个 share 添加多个 DB，实现跨域联邦数据访问。[attached_file:45b2b215-7882-4ad6-9a35-72e891ed6add]

