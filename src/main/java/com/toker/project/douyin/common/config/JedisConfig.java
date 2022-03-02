package com.toker.project.douyin.common.config;

import lombok.Data;

import java.io.Serializable;

/**
 * Jedis配置
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 20:47
 * @modified mdmbct
 * @since 1.0
 */
@Data
public class JedisConfig implements Serializable {

    private static final long serialVersionUID = -8932909016942174741L;

    /**
     * 主机地址.
     */
    private String host = "127.0.0.1";

    /**
     * 端口号.
     */
    private int port = 6379;

    /**
     * 密码.
     */
    private String password;

    /**
     * 超时.
     */
    private int timeout = 3000;

    /**
     * 数据库.
     */
    private int database = 0;

    /**
     * 最大活动对象数
     */
    private Integer maxTotal;

    /**
     * 最大能够保持idel状态的对象数
     */
    private Integer maxIdle;

    /**
     * 当池内没有返回对象时，最大等待时间   -1没有限制
     */
    private Integer maxWaitMillis;

    private Integer minIdle;
}
