package com.haenu.shortlink.common.constant;

/**
 * 短链接后台 Redis 常量类
 *
 * @author haenu
 * @version 1.0
 * @date 2023/12/18 11:01
 */
public class RedisCacheConstant {
    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";


    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";


    /**
     * 用户登录缓存标识
     */
    public static final String USER_LOGIN_KEY = "short-link:login:";

}

