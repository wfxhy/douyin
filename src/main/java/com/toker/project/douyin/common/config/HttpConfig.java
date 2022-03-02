package com.toker.project.douyin.common.config;

import com.toker.project.douyin.common.enums.HttpClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * http配置
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 20:27
 * @modified mdmbct
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpConfig implements Serializable {

    private static final long serialVersionUID = 6687112188379930466L;


    /**
     * 发送http请求的实现方式 默认{@link HttpClientType#OK_HTTP}
     */
    @Builder.Default
    private HttpClientType type = HttpClientType.OK_HTTP;

    /**
     * 请求失败重试次数 默认3次 最多10次
     */
    @Builder.Default
     private int maxRetry = 3;

    /**
     * 从发起请求到响应的时间间隔 默认5 * 60 * 1000ms （5min）
     */
    @Builder.Default
    private long responseTimeout = 300_000;

    /**
     * 连接时间 默认5 * 1000ms
     */
    @Builder.Default
    private long connectTimeout = 5_000;
}
