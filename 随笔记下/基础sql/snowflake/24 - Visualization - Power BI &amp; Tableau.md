<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 24 - Visualization - Power BI \& Tableau

Snowflake 提供了原生的连接器，能够无缝集成 Power BI 和 Tableau 等主流 BI 工具。连接的核心只需账户的 URL（Server）和仓库信息，均支持“实时查询”或“数据提取”两种模式。[attached_file:6e4b0ae5-a7aa-4d3a-91a0-924894399a68]

## Power BI 连接指南

Power BI Desktop（仅支持 Windows）提供了内置的 Snowflake 连接器。

1. **建立连接**：在“Home”选项卡点击 **Get Data** > **More...**，搜索并选择 **Snowflake**。
2. **配置服务器**：
    * **Server**：输入 Snowflake 账户的 URL（例如 `xy12345.us-east-1.snowflakecomputing.com`）。注意通常不需要 `https://` 前缀。
    * **Warehouse**：可选填，指定默认使用的计算仓库（如 `COMPUTE_WH`）。
3. **选择数据连接模式**：
    * **Import**：将数据导入 Power BI 的本地缓存。适合较小数据集，查询速度快，但数据不是实时的。
    * **DirectQuery**：数据保留在 Snowflake 中，每次用户交互都会向 Snowflake 发送 SQL 查询。适合大数据集，数据实时，但性能依赖于 Snowflake 仓库。
4. **身份验证**：选择 **Basic**（用户名/密码）或 **Microsoft Account**（如果是 SSO 登录）。连接成功后，在导航器中选择需要的表并点击 **Load**。[attached_file:ad21dc1a-fedf-4bf6-b829-a1b3247eed59][attached_file:5e7d0a45-017d-411b-ab9a-c606b861c277]

## Tableau 连接指南

Tableau Desktop 同样内置了 Snowflake 驱动（如果没有需单独安装驱动）。

1. **建立连接**：在启动页面的“Connect”栏下选择 **To a Server** > **More...** > **Snowflake**。
2. **配置与验证**：
    * 输入 **Server** 地址。
    * 选择 **Authentication** 方式（通常是 Username and Password 或 OAuth）。
    * 建议在连接时指定 **Role**（角色），否则将使用用户的默认角色。
3. **选择数据对象**：登录后，依次从下拉菜单中选择 Warehouse、Database 和 Schema，然后将所需的表拖入画布。
4. **连接类型**：
    * **Live**：相当于 DirectQuery，实时查询 Snowflake。
    * **Extract**：相当于 Import，将数据提取到 Tableau 的本地 `.hyper` 文件中。[attached_file:857f7b52-bd7b-4475-8b85-903afa9584f1][attached_file:8c59a088-632b-41e1-996f-a2629a32a6ef]

**注意**：这两个工具都允许你在可视化层定义数据模型（Model/Relationships），例如将 `Orders` 表和 `Customers` 表关联起来，以便在报告中进行跨表分析。[attached_file:c94a2e23-6cdd-42db-97c9-d289f0eb17a0]

