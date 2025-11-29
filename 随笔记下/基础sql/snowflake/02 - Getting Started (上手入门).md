<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

### 02 - Getting Started (上手入门)

这一章是 **Snowflake 实战入门**，教你从零开始用 Snowflake。结合你的 PDF 幻灯片和附件笔记，我把核心步骤和 SQL 示例整理如下。

#### **1. Snowflake 界面操作流程**

* **登录 \& Worksheets (工作表)**：
* 打开 Snowflake Web UI，左侧栏选 **Worksheets**。
* 新建 Worksheet（像 VS Code 一样），选角色（默认 `ACCOUNTADMIN` 或 `SYSADMIN`）。
* **三板斧**：切换 Database/Schema/Warehouse（右上角下拉菜单）。


#### **2. 核心上手命令（你的笔记精华）**

从你的附件 `003-Create-a-database.txt` 和 `012-Loading-data.txt` 中提取：

**步骤1：创建数据库**

```sql
-- 从官方 Sample Data Share 创建样例数据库（超方便！）
CREATE DATABASE snowflake_sample_data 
FROM SHARE sfc_samples.sample_data;

-- 授权 PUBLIC 角色访问（所有人都能看）
GRANT IMPORTED PRIVILEGES ON DATABASE snowflake_sample_data TO ROLE PUBLIC;
```

**步骤2：重命名数据库 + 建表**

```sql
-- 重命名（课程中会创建 FIRST_DB，然后改名）
ALTER DATABASE FIRST_DB RENAME TO OUR_FIRST_DB;

-- 创建贷款数据表（全 STRING 类型，适合 CSV 导入）
CREATE TABLE "OUR_FIRST_DB"."PUBLIC"."LOAN_PAYMENT" (
  "Loan_ID" STRING,
  "loan_status" STRING,
  "Principal" STRING,
  "terms" STRING,
  "effective_date" STRING,
  "due_date" STRING,
  "paid_off_time" STRING,
  "past_due_days" STRING,
  "age" STRING,
  "education" STRING,
  "Gender" STRING
);
```

**注意**：课程用 STRING 类型是为了 **宽松导入** CSV（后面优化性能时再转 NUMBER/DATE）。[^1]

**步骤3：验证空表**

```sql
USE DATABASE OUR_FIRST_DB;
SELECT * FROM LOAN_PAYMENT;  -- 应该返回 0 rows
```

**步骤4：COPY 加载数据（课程核心）**

```sql
-- 从 S3 Bucket 加载 CSV（这是 Bulk Loading 的入门）
COPY INTO LOAN_PAYMENT
FROM 's3://bucketsnowflakes3/Loan_payments_data.csv'
FILE_FORMAT = (
  TYPE = 'CSV'
  FIELD_DELIMITER = ','
  SKIP_HEADER = 1  -- 跳过 CSV 第一行表头
);
```

**验证加载**：

```sql
SELECT * FROM LOAN_PAYMENT LIMIT 10;  -- 看前 10 行
```


#### **3. 入门注意事项（来自 PDF 最佳实践）**

* **Warehouse 选择**：用 XS 或 S 练手（便宜）。加载数据时确保 Warehouse 已启动。
* **Stage 概念**：`COPY` 前不需要显式建 Stage，Snowflake 会自动识别 S3 URL。
* **权限**：用 `SYSADMIN` 角色操作，避免用 `ACCOUNTADMIN`（安全原则）。
* **常见错误**：
* `Warehouse not running`：手动 Resume Warehouse。
* `Access denied to S3`：需要配置 **Storage Integration**（后面章节讲）。


#### **4. 实战建议**

1. **立即试跑**：用你的 Trial Account，按上面 SQL 一条条执行。
2. **观察界面**：加载时看 **Query History**（右下角），能看到 Pruning、Spill 等底层细节。
3. **下一步预习**：02 结束后直接进 **03 Architecture** 和 **04 Loading Data**，COPY 是 Snowflake 的灵魂操作。

**记忆口诀**：**"Create DB → Build Table → COPY from S3 → SELECT Validate"**。这 4 步走通，你就上手了！[^1]
<span style="display:none">[^2]</span>

<div align="center">⁂</div>

[^1]: 012-Loading-data.txt

[^2]: 003-Create-a-database.txt

