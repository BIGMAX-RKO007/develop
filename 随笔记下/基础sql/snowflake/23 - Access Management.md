<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# 23 - Access Management

Snowflake 采用基于角色的访问控制（RBAC）模型，用户通过被授予的角色（Role）获取对数据库对象（如表、视图）的操作权限，而非直接授权给用户。

## 系统角色层级

Snowflake 预置了几个关键系统角色，形成层级结构：

- **ACCOUNTADMIN**: 顶级角色，拥有所有权限，包括账户级配置（如计费、网络策略）。应严格限制使用，建议开启 MFA。[attached_file:fd6ed349-58b3-47ed-b505-9bdf5f701589]
- **SECURITYADMIN**: 负责安全管理，拥有 `MANAGE GRANTS` 权限，可以创建、修改、删除用户和角色，并管理所有权限授予。
- **USERADMIN**: 专门用于用户和角色管理的角色，是 SECURITYADMIN 的子集。它可以创建用户和角色，并管理它自己创建的对象，但不能管理所有全局授权。[attached_file:528b0322-e0d0-456c-968f-2495435d2901][^1]
- **SYSADMIN**: 拥有创建仓库、数据库等对象的权限。建议将自定义角色的层级最终汇总授予给 SYSADMIN，以便系统管理员统一管理对象。
- **PUBLIC**: 默认授予所有用户和角色的伪角色。对此角色的授权会自动应用于所有用户，通常用于授予基本的登录或通用访问权限。[attached_file:b00885ef-0f7b-4be9-8dfa-adb120d1b593]


## 最佳实践

- 避免直接使用 ACCOUNTADMIN 处理日常任务。
- 创建自定义角色（如 `HR_ADMIN`, `ANALYST`）以满足特定业务需求，并建立层级关系（Hierarchy）。
- 遵循最小权限原则，仅授予完成任务所需的最小权限集。

<div align="center">⁂</div>

[^1]: 012-USERADMIN.txt

