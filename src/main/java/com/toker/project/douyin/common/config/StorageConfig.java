package com.toker.project.douyin.common.config;

import com.toker.project.douyin.common.enums.StorageType;
import lombok.Data;

import java.io.Serializable;

/**
 * 存储配置
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 13:44
 * @modified mdmbct
 * @since 1.0
 */
@Data
public class StorageConfig implements Serializable {

    private static final long serialVersionUID = -1206287313080926756L;

    /**
     * 存储类型 默认使用redis_template
     */
    private StorageType type = StorageType.REDIS_TEMPLATE;

    /**
     * 使用{@link StorageType#MEMORY}时 内存中存储的配置和token存储在硬盘中的位置 只需要设置文件夹<p>
     * 默认存储位置./data/dy
     */
    private String memoryLocalSave = "./data/dy";

    /**
     * jedis pool配置<p>
     * 使用{@link StorageType#JEDIS}需配置<p>
     * 如果不配置 则寻找IOC中的JedisPool实例
     */
    private JedisConfig jedis;

}
