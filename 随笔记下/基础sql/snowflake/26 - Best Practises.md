<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 26 - Best Practises

以下是关于 Snowflake 的最佳实践总结，涵盖性能优化、成本控制和监控。[attached_file:490d24b8-9bb5-41d8-b641-908219f041c4]

## 性能优化

* **虚拟仓库（Virtual Warehouse）**：启用 **Auto-Suspend**（自动挂起）并设置较短的时间（如 60-300 秒），避免在空闲时持续计费。启用 **Auto-Resume**（自动恢复）以确保查询无缝执行。对于并发查询负载，使用 **Multi-Cluster Warehouses**（多集群仓库）自动扩展。
* **表设计**：对于大型表，合理选择 **Clustering Keys**（聚簇键）以利用 Partition Pruning（分区修剪）加速查询。优先使用整数类型的列作为聚簇键，并避免对高基数列进行聚簇。[attached_file:a3001572-a635-413f-97b8-00a4465cb39c][attached_file:df53f6ef-852b-47d4-a9c3-9fc77cea3df0]


## 成本管理与监控

* **资源监控**：设置 **Resource Monitors**（资源监控器）来限制或通知每月的信用消耗（Credits Usage）。
* **Time Travel与Fail-safe**：为不需要历史回溯的临时表（Transient/Temporary Tables）设置较短的 Time Travel 保留期（如 0 或 1 天），以节省存储成本。[attached_file:cfeb0ab3-5333-4cd0-8bee-6b891b38f2d1]
* **监控查询**：利用 `SNOWFLAKE.ACCOUNT_USAGE` 模式下的视图，如 `QUERY_HISTORY` 监控慢查询，`WAREHOUSE_METERING_HISTORY` 分析仓库信用消耗，以及 `TABLE_STORAGE_METRICS` 跟踪存储增长。[attached_file:c6f1c8d9-40e1-4921-86c0-904ce7a11c12][^1]


## 安全性

* 使用 **RBAC**（基于角色的访问控制）而不是直接授权给用户。
* 启用 **MFA**（多因素认证）保护账户，尤其是 ACCOUNTADMIN 角色。
* 通过 **Network Policies**（网络策略）限制允许访问的 IP 地址范围。

<div align="center">⁂</div>

[^1]: 004-Monitoring-Resources.txt

