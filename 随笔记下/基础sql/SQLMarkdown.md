
# 🧭 SQL 执行顺序与关键字速查表

> 本文帮助你快速理解 SQL 的“书写顺序”和“执行顺序”，并附上常用关键字、功能分类与记忆逻辑。  
> ——整理：樊宵学习笔记版

---

## 🧩 一、SQL 的两种顺序

### ✍️ 书写顺序（我们写 SQL 的顺序）

```sql
SELECT 查询字段
FROM 表
[JOIN 表2 ON 连接条件]
[WHERE 筛选条件]
[GROUP BY 分组字段]
[HAVING 分组后筛选]
[ORDER BY 排序字段]
[LIMIT 偏移量, 行数]
````

### ⚙️ 执行顺序（数据库实际执行的逻辑）

| 执行顺序 | 关键字                    | 说明      |
| ---- | ---------------------- | ------- |
| ①    | FROM                   | 指定数据来源  |
| ②    | JOIN / ON              | 联表与连接条件 |
| ③    | WHERE                  | 过滤原始数据  |
| ④    | GROUP BY               | 分组      |
| ⑤    | HAVING                 | 分组后过滤   |
| ⑥    | SELECT                 | 选择字段    |
| ⑦    | DISTINCT               | 去重      |
| ⑧    | ORDER BY               | 对结果排序（可用 ASC / DESC）      |
| ⑨    | LIMIT / OFFSET / FETCH | 限制返回的行数 |

> 🧠 记忆口诀：「从（FROM）哪儿来 → 怎么连（JOIN）→ 先过滤（WHERE）→ 再分组（GROUP）→ 后筛选（HAVING）→ 选字段（SELECT）→ 排顺序（ORDER）→ 截结果（LIMIT）」

---

## 🧱 二、主要关键字汇总（按执行阶段分类）

### 1️⃣ 数据来源阶段（FROM）

| 关键字                                       | 功能          | 示例                                         |
| ----------------------------------------- | ----------- | ------------------------------------------ |
| FROM                                      | 指定主表        | `FROM users`                               |
| JOIN / LEFT JOIN / RIGHT JOIN / FULL JOIN | 联表          | `JOIN orders ON users.id = orders.user_id` |
| CROSS JOIN / LATERAL JOIN                 | 特殊连接        | 适用于行级函数展开                                  |
| ON                                        | 连接条件        | `ON a.id = b.user_id`                      |
| WITH / WITH RECURSIVE                     | 公共表表达式（CTE） | `WITH temp AS (SELECT ...)`                |
| TABLESAMPLE                               | 抽样          | 从大表中随机取样                                   |
| SUBQUERY                                  | 子查询当表       | `FROM (SELECT ...) AS t`                   |

---
WITH recent_orders AS (
SELECT * FROM orders WHERE order_date > '2024-01-01'
)
SELECT u.name, o.total_amount
FROM users u
JOIN recent_orders o ON u.id = o.user_id;
---

### 2️⃣ 过滤阶段（WHERE / GROUP / HAVING）

| 关键字                      | 功能     | 示例                                           |
| ------------------------ | ------ | -------------------------------------------- |
| WHERE                    | 原始行过滤  | `WHERE age > 20`                             |
| GROUP BY                 | 按字段分组  | `GROUP BY department`                        |
| HAVING                   | 分组后过滤  | `HAVING COUNT(*) > 5`                        |
| CASE WHEN THEN ELSE END  | 条件判断   | `SELECT CASE WHEN age > 18 THEN 'adult' END` |
| EXISTS / IN / ANY / ALL  | 子查询判断  | `WHERE EXISTS (SELECT 1 ...)`                |
| BETWEEN / LIKE / IS NULL | 常见条件运算 | `WHERE name LIKE 'A%'`                       |

---
SELECT department_id, COUNT(*) AS emp_count
FROM employees
WHERE hire_date >= '2020-01-01'
GROUP BY department_id
HAVING COUNT(*) > 10;
---
SELECT order_id,
CASE
WHEN order_amount > 1000 THEN '大额'
WHEN order_amount > 500 THEN '中额'
ELSE '小额'
END AS order_type
FROM orders;
---

### 3️⃣ 输出阶段（SELECT）

| 关键字               | 功能     | 示例                                                 |
| ----------------- | ------ | -------------------------------------------------- |
| SELECT            | 选择输出字段 | `SELECT name, age`                                 |
| DISTINCT          | 去重     | `SELECT DISTINCT city`                             |
| WINDOW / OVER()   | 窗口函数   | `ROW_NUMBER() OVER(PARTITION BY dept ORDER BY id)` |
| AS                | 别名     | `SELECT name AS user_name`                         |
| COALESCE / NULLIF | 空值处理   | `COALESCE(salary, 0)`                              |

---
SELECT
department_id,
employee_name,
salary,
ROW_NUMBER() OVER (PARTITION BY department_id ORDER BY salary DESC) AS rank
FROM employees;
---

### 4️⃣ 排序与限制阶段（ORDER / LIMIT）

| 关键字                      | 功能        | 示例                               |
| ------------------------ | --------- | -------------------------------- |
| ORDER BY                 | 排序        | `ORDER BY create_at DESC`        |
| NULLS FIRST / NULLS LAST | 空值排序      | `ORDER BY score DESC NULLS LAST` |
| LIMIT                    | 限制结果数     | `LIMIT 10`                       |
| OFFSET                   | 跳过行       | `LIMIT 10 OFFSET 20`             |
| FETCH FIRST n ROWS ONLY  | ANSI 标准分页 | `FETCH FIRST 10 ROWS ONLY`       |

---
SELECT id, name, created_at
FROM users
ORDER BY created_at DESC
LIMIT 5;
---

### 5️⃣ 集合操作（多 SELECT 合并）

| 关键字            | 功能    | 示例                                |
| -------------- | ----- | --------------------------------- |
| UNION          | 去重合并  | `SELECT ... UNION SELECT ...`     |
| UNION ALL      | 不去重合并 | `SELECT ... UNION ALL SELECT ...` |
| INTERSECT      | 取交集   | `SELECT ... INTERSECT SELECT ...` |
| EXCEPT / MINUS | 取差集   | `SELECT ... EXCEPT SELECT ...`    |

---
SELECT city FROM users
UNION ALL
SELECT city FROM customers
ORDER BY city;
---

### 6️⃣ 数据操作类（非查询）

| 关键字                         | 功能              | 示例                                                |
| --------------------------- | --------------- | ------------------------------------------------- |
| INSERT INTO ... SELECT      | 插入查询结果          | `INSERT INTO new_table SELECT * FROM old_table`   |
| UPDATE ... SET ... FROM ... | 多表更新            | `UPDATE a SET name=b.name FROM b WHERE a.id=b.id` |
| DELETE FROM ... USING ...   | 条件删除            | `DELETE FROM a USING b WHERE a.id=b.id`           |
| MERGE INTO ... USING ...    | UPSERT（合并更新/插入） | 数据同步场景常用  `MERGE INTO target t USING source s ON t.id = s.id WHEN MATCHED THEN UPDATE SET ... WHEN NOT MATCHED THEN INSERT ... `                                       |
| EXPLAIN / ANALYZE           | 执行计划分析          | 性能调优使用  `EXPLAIN ANALYZE SELECT * FROM orders `                                         |
| LOCK / FOR UPDATE           | 行锁定             | 防止并发冲突                                            |

---

## 📚 七、完整执行顺序表（终极版）

| 顺序 | 阶段   | 关键字                        | 说明        |
| -- | ---- | -------------------------- | --------- |
| 0  | 前置   | WITH / WITH RECURSIVE      | 定义临时结果    |
| 1  | 数据来源 | FROM                       | 指定主表      |
| 2  | 联接   | JOIN / APPLY / ON          | 关联其他表     |
| 3  | 过滤   | WHERE                      | 过滤行       |
| 4  | 分组   | GROUP BY                   | 分组        |
| 5  | 分组过滤 | HAVING                     | 对聚合后数据再筛选 |
| 6  | 投影   | SELECT / DISTINCT / WINDOW | 选择输出      |
| 7  | 集合操作 | UNION / INTERSECT / EXCEPT | 合并结果      |
| 8  | 排序   | ORDER BY / NULLS LAST      | 排序输出      |
| 9  | 限制   | LIMIT / OFFSET / FETCH     | 截取结果      |

---

## 🎯 快速记忆口诀

> **从哪来（FROM） → 怎么连（JOIN） → 先过滤（WHERE） → 再分组（GROUP） → 后筛选（HAVING） → 选字段（SELECT） → 排顺序（ORDER） → 截结果（LIMIT）**

---

## 🧠 小贴士

* SQL 的**写法**和**执行逻辑**不是一个顺序，要牢记区别。
* `WHERE` 过滤的是原始行，`HAVING` 过滤的是分组后的聚合结果。
* `ORDER BY` 必须放在 `LIMIT` 前，否则报错。
* 复杂查询尽量用 `WITH`（CTE） 提高可读性。
* UNION ALL 比 UNION 性能高，因为不做去重。
* WINDOW 函数不会改变行数，但能实现分组内排序、累计等高级统计。



