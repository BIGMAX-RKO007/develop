<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 18 - Data Sampling

数据采样在 Snowflake 中用来在不扫描全表的情况下，用一小部分代表性数据做分析或验证，从而降低成本、加快查询速度。Snowflake 内置 `SAMPLE` 语法，支持多种采样方式，并且可以通过 SEED 保证采样可重复。[attached_file:bd93d991-cd02-4722-8fe6-95f3aab85db2]

## 概念与使用场景

数据采样的核心思想是“用一小部分数据近似代表整体”，在数据仓库里常用于探索数据分布、开发/调试 SQL、做 PoC 或测试报表而不必动辄扫描 TB 级表。
在 Snowflake 中，对大表直接 `SELECT ... FROM big_table` 非常耗费计算资源，而加上 `SAMPLE` 只扫描部分微分区，可以显著减少 warehouse 计算时间和费用。

## Snowflake 采样方法

Snowflake 提供两类常用采样：

- 行级随机采样：`SAMPLE ROW (p)` 按行概率进行 Bernoulli 采样，例如 `SAMPLE ROW (1)` 表示大约 1% 的行会被选中。[attached_file:bd93d991-cd02-4722-8fe6-95f3aab85db2]
- 系统级采样：`SAMPLE SYSTEM (p)` 按存储微分区（block）抽样，IO 更高效但粒度较粗，常用于非常大的事实表，比如 `SAMPLE SYSTEM (10)`。[attached_file:bd93d991-cd02-4722-8fe6-95f3aab85db2]

两种方式都支持 `SEED <n>`，在相同 SEED 下重复执行可以得到同样的样本集，便于调试和结果对比。[attached_file:bd93d991-cd02-4722-8fe6-95f3aab85db2]

## 示例：采样视图与分析

示例脚本中先创建一个用于练习的瞬态数据库 `SAMPLINGDB`，然后基于官方示例库 `SNOWFLAKE_SAMPLE_DATA.TPCDSSF10TCL.CUSTOMERADDRESS` 创建视图 `ADDRESSSAMPLE`，视图内部使用 `SAMPLE ROW (1) SEED 27` 从大表抽取约 1% 的地址数据。[attached_file:bd93d991-cd02-4722-8fe6-95f3aab85db2]
接着在这个样本视图上做分组统计，例如按 `CA_LOCATION_TYPE` 统计计数，就能快速预估各类地址占比；也可以直接对源表使用 `SAMPLE SYSTEM (1)` 与 `SAMPLE SYSTEM (10)` 对比不同采样率对结果和性能的影响。[attached_file:bd93d991-cd02-4722-8fe6-95f3aab85db2]
<span style="display:none">[^1]</span>

<div align="center">⁂</div>

[^1]: 003-Data-Sampling.txt

