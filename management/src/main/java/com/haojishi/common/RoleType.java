package com.haojishi.common;

/**
 * 角色类型
 * <p>
 * Created by SongpoLiu on 2017/5/28.
 */
public enum RoleType {
    ROLE_NONE, // 空，只是为了跳过枚举的0这个情况
    ROLE_ADMIN, // 1管理员
    ROLE_WORKER, // 2运维人员
    ROLE_USER,  // 3财务人员
    ROLE_CUSTOMER, // 4客服
    ROLE_AGENT, // 5代理商
    ROLE_GOLD, // 6金牌管理员
    ROLE_STRATEGY // 7战略合伙人管理员
}
