package com.toker.project.douyin.common.enums;

/**
 * 存储类型
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 13:20
 * @modified mdmbct
 * @since 1.0
 */
public enum StorageType {

    /**
     * 使用ConcurrentHashMap存储在内存中 只支持单机
     */
    MEMORY,
    /**
     * 存储在Redis中 使用redisTemplate操作redis 读取spring.redis配置
     */
    REDIS_TEMPLATE,

    /**
     * 使用jedis连接redis 读取dy.storage.redis中的配置
     */
    JEDIS,
    ;
}
