太棒了 👍 那我就帮你整理一份最实用的——
**《SQL 常用关键字速查表（Markdown 中文版）》**
这份文档既可以学习，也可以作为工作中的快速参考。

---

# 🧠 SQL 常用关键字速查表（中文版）

> 💡 说明：适用于主流数据库（MySQL / PostgreSQL / SQL Server / Oracle 等）
> ✅ 常用关键字按照逻辑分类整理，附简要说明与示例。

---

## 🧩 一、查询与输出类

| 关键字        | 说明       | 示例                                        |
| :--------- | :------- | :---------------------------------------- |
| `SELECT`   | 选择要查询的字段 | `SELECT name, age FROM users;`            |
| `AS`       | 给表或字段取别名 | `SELECT name AS 姓名 FROM users AS u;`      |
| `DISTINCT` | 去除重复结果   | `SELECT DISTINCT country FROM users;`     |
| `FROM`     | 指定数据来源表  | `SELECT * FROM customers;`                |
| `WHERE`    | 指定筛选条件   | `SELECT * FROM orders WHERE price > 100;` |

---

## 🔗 二、表连接与关系类

| 关键字          | 说明                | 示例                                             |
| :----------- | :---------------- | :--------------------------------------------- |
| `JOIN`       | 默认内连接（Inner Join） | `SELECT * FROM A JOIN B ON A.id = B.id;`       |
| `INNER JOIN` | 内连接：匹配两表共有数据      | `SELECT * FROM A INNER JOIN B ON A.id = B.id;` |
| `LEFT JOIN`  | 左连接：保留左表全部数据      | `SELECT * FROM A LEFT JOIN B ON A.id = B.id;`  |
| `RIGHT JOIN` | 右连接：保留右表全部数据      | `SELECT * FROM A RIGHT JOIN B ON A.id = B.id;` |
| `FULL JOIN`  | 全连接（部分数据库支持）      | `SELECT * FROM A FULL JOIN B ON A.id = B.id;`  |
| `ON`         | 指定连接条件            | `ON A.id = B.id`                               |

---

## ⚙️ 三、条件与判断类

| 关键字                       | 说明          | 示例                                                       |
| :------------------------ | :---------- | :------------------------------------------------------- |
| `AND` / `OR`              | 多条件逻辑连接     | `WHERE age > 20 AND gender = 'M'`                        |
| `NOT`                     | 取反          | `WHERE NOT (price < 100)`                                |
| `IN` / `NOT IN`           | 匹配集合内的值     | `WHERE city IN ('Tokyo', 'Osaka')`                       |
| `BETWEEN ... AND ...`     | 范围查询        | `WHERE age BETWEEN 20 AND 30`                            |
| `LIKE` / `NOT LIKE`       | 模糊查询        | `WHERE name LIKE '%樊%'`                                  |
| `IS NULL` / `IS NOT NULL` | 判断是否为空      | `WHERE email IS NOT NULL`                                |
| `EXISTS`                  | 判断子查询结果是否存在 | `WHERE EXISTS (SELECT 1 FROM orders WHERE user_id=u.id)` |

---

## 📊 四、分组与聚合类

| 关键字               | 说明        | 示例                               |
| :---------------- | :-------- | :------------------------------- |
| `GROUP BY`        | 按字段分组     | `GROUP BY department`            |
| `HAVING`          | 对分组后的结果筛选 | `HAVING COUNT(*) > 3`            |
| `COUNT()`         | 统计数量      | `SELECT COUNT(*) FROM users;`    |
| `SUM()`           | 求和        | `SELECT SUM(price) FROM orders;` |
| `AVG()`           | 平均值       | `SELECT AVG(score) FROM exams;`  |
| `MAX()` / `MIN()` | 最大值 / 最小值 | `SELECT MAX(age) FROM users;`    |

---

## 🧮 五、排序与分页类

| 关键字        | 说明                         | 示例                            |
| :--------- | :------------------------- | :---------------------------- |
| `ORDER BY` | 对结果排序                      | `ORDER BY price DESC`         |
| `ASC`      | 升序（默认）                     | `ORDER BY name ASC`           |
| `DESC`     | 降序                         | `ORDER BY price DESC`         |
| `LIMIT`    | 限制结果条数（MySQL / PostgreSQL） | `LIMIT 0,10`                  |
| `OFFSET`   | 从第几行开始（配合 LIMIT）           | `LIMIT 10 OFFSET 20`          |
| `TOP`      | SQL Server 指定前 N 条         | `SELECT TOP 10 * FROM users;` |

---

## 🧱 六、集合与子查询类

| 关键字                | 说明                       | 示例                                                 |
| :----------------- | :----------------------- | :------------------------------------------------- |
| `UNION`            | 合并多个查询（去重）               | `SELECT name FROM A UNION SELECT name FROM B;`     |
| `UNION ALL`        | 合并多个查询（不去重）              | `SELECT name FROM A UNION ALL SELECT name FROM B;` |
| `INTERSECT`        | 取交集（部分数据库支持）             | `SELECT name FROM A INTERSECT SELECT name FROM B;` |
| `EXCEPT` / `MINUS` | 取差集（PostgreSQL / Oracle） | `SELECT name FROM A EXCEPT SELECT name FROM B;`    |
| `(SELECT ...)`     | 子查询                      | `WHERE id IN (SELECT user_id FROM orders);`        |

---

## 🧰 七、数据操作类（DML）

| 关键字           | 说明   | 示例                                                |
| :------------ | :--- | :------------------------------------------------ |
| `INSERT INTO` | 插入数据 | `INSERT INTO users(name, age) VALUES ('樊宵', 33);` |
| `UPDATE`      | 更新数据 | `UPDATE users SET age = 34 WHERE id = 1;`         |
| `DELETE`      | 删除数据 | `DELETE FROM users WHERE id = 1;`                 |

---

## 🧱 八、数据定义类（DDL）

| 关键字              | 说明    | 示例                                              |
| :--------------- | :---- | :---------------------------------------------- |
| `CREATE TABLE`   | 创建表   | `CREATE TABLE users(id INT, name VARCHAR(50));` |
| `ALTER TABLE`    | 修改表结构 | `ALTER TABLE users ADD COLUMN age INT;`         |
| `DROP TABLE`     | 删除表   | `DROP TABLE users;`                             |
| `TRUNCATE TABLE` | 清空表   | `TRUNCATE TABLE users;`                         |

---

## 🧑‍💻 九、权限与事务控制类

| 关键字                             | 说明        | 示例                                |
| :------------------------------ | :-------- | :-------------------------------- |
| `GRANT` / `REVOKE`              | 授权 / 撤销权限 | `GRANT SELECT ON users TO guest;` |
| `BEGIN` / `COMMIT` / `ROLLBACK` | 事务控制      | `BEGIN; UPDATE ...; COMMIT;`      |
| `SAVEPOINT`                     | 设置事务回滚点   | `SAVEPOINT before_update;`        |

---

## 🌐 十、数据库管理类

| 关键字               | 说明    | 示例                      |
| :---------------- | :---- | :---------------------- |
| `CREATE DATABASE` | 创建数据库 | `CREATE DATABASE shop;` |
| `USE`             | 切换数据库 | `USE shop;`             |
| `SHOW TABLES`     | 显示所有表 | `SHOW TABLES;`          |
| `DESCRIBE`        | 查看表结构 | `DESCRIBE users;`       |

---

## 🧭 示例：完整 SQL 综合示例

```sql
SELECT
    c.customer_name AS 客户名,
    COUNT(o.id) AS 订单数,
    SUM(o.amount) AS 总金额
FROM
    customers AS c
LEFT JOIN
    orders AS o
ON
    c.customer_id = o.customer_id
WHERE
    o.order_date >= '2024-01-01'
GROUP BY
    c.customer_name
HAVING
    SUM(o.amount) > 1000
ORDER BY
    总金额 DESC
LIMIT
    0, 10;
```

---

是否希望我帮你再生成一个 **可打印的 PDF 格式版本**（带目录、表格样式优化、代码高亮），方便你随时翻阅？
