<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 22 - Dynamic Data Masking

Snowflake 动态数据脱敏（Dynamic Data Masking）是一种列级安全功能，它使用脱敏策略（Masking Policy）根据请求用户的角色实时决定返回原始数据还是掩码数据（如 `***` 或哈希值），而不改变存储在数据库中的实际数据。[^1]

## 创建脱敏策略

使用 `CREATE OR REPLACE MASKING POLICY policy_name AS (val type) RETURNS type -> expression` 语法。表达式通常包含 `CASE` 语句，根据 `CURRENT_ROLE()` 返回不同结果。

示例：创建策略，仅允许 `ANALYST_FULL` 角色查看完整电话号码，其他角色只看到掩码 `##-###`。

```sql
CREATE OR REPLACE MASKING POLICY phone_mask AS (val string) RETURNS string ->
  CASE
    WHEN CURRENT_ROLE() IN ('ANALYST_FULL', 'ACCOUNTADMIN') THEN val
    ELSE '##-###'
  END;
```


## 应用与管理策略

通过 `ALTER TABLE ... MODIFY COLUMN ... SET MASKING POLICY` 将策略绑定到特定列。同一策略可应用于多个表的多个字段，实现集中管理。

```sql
ALTER TABLE customers MODIFY COLUMN phone SET MASKING POLICY phone_mask;
```

要解除绑定，使用 `UNSET MASKING POLICY`。常见的脱敏方式包括完全遮蔽、部分遮蔽（如保留邮箱域名）、哈希处理（SHA2）或替换为固定值（如日期替换为 `0001-01-01`）。[^2][^1]

<div align="center">⁂</div>

[^1]: 002-Create-masking-policy.txt

[^2]: 005-Real-life-examples.txt

