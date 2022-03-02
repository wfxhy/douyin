package com.toker.project.douyin.common.enums;

/**
 * 认证的open api平台
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 16:35
 * @modified mdmbct
 * @since 1.0
 */
public enum ApiPlatform {

    /**
     * 抖音
     */
    DOU_YIN(0, "", "https://open.douyin.com"),

    /**
     * 西瓜
     */
    XI_GUA(1, "/xigua", "https://open-api.ixigua.com"),

    /**
     * 头条
     */
    SNS(2, "/toutiao", "https://open.snssdk.com");

    private final int code;

    /**
     * 路径开头
     */
    private final String pathPrefix;

    private final String prefix;

    ApiPlatform(int code, String pathPrefix, String prefix) {
        this.code = code;
        this.pathPrefix = pathPrefix;
        this.prefix = prefix;
    }

    public int getCode() {
        return code;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
