--算数运算
select 6+2;
select 6-2;
select 6*2;
select 6/2;
select 6%2;
select least(20 ,30) as small_num; --求最小值,不可为null
select greatest(20,30) as big_num; --求最大值,不可为null

/*
	条件查询
	标准语法：
		SELECT 列名列表 FROM 表名 WHERE 条件;
*/

--某 int double 列上涨10%
SELECT stock * 1.1 as new_stock FROM product;
--比较运算符
-- 查询库存大于20的商品信息
SELECT * FROM product WHERE stock > 20;
-- 查询品牌为华为的商品信息
SELECT * FROM product WHERE brand='华为';
--不等于
SELECT * FROM product WHERE brand != '华为';
SELECT * FROM product WHERE not (brand = '华为');
SELECT * FROM product WHERE brand <> '华为';
-- 查询金额在4000 ~ 6000之间的商品信息
SELECT * FROM product WHERE price >= 4000 AND price <= 6000;
SELECT * FROM product WHERE price >= 4000 && price <= 6000;  --逻辑与
SELECT * FROM product WHERE price BETWEEN 4000 AND 6000;
-- 查询库存为14、30、23的商品信息
--逻辑运算符
SELECT * FROM product WHERE stock=14 OR stock=30 OR stock=23;
SELECT * FROM product WHERE stock=14 OR stock=30 || stock=23;  --逻辑或
SELECT * FROM product WHERE stock IN(14,30,23);

-- 查询库存为null的商品信息
SELECT * FROM product WHERE stock IS NULL;

-- 查询库存不为null的商品信息
SELECT * FROM product WHERE stock IS NOT NULL;

--匹配运算符
-- 查询名称以小米为开头的商品信息
SELECT * FROM product WHERE NAME LIKE '小米%';

-- 查询名称第二个字是为的商品信息
SELECT * FROM product WHERE NAME LIKE '_为%';

-- 查询名称为四个字符的商品信息
SELECT * FROM product WHERE NAME LIKE '____';

-- 查询名称中包含电脑的商品信息
SELECT * FROM product WHERE NAME LIKE '%电脑%';