package com.toker.project.douyin.common.config;

import com.toker.project.douyin.common.enums.ApiPlatform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 抖音应用配置
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/19 20:31
 * @modified mdmbct
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig implements Serializable {

    private static final long serialVersionUID = 2705895429997839179L;

    private String key;

    private String secret;

    /**
     * 回调地址
     */
    private String redirectUrl;

    /**
     * 默认开发平台地址 默认
     * <a href="https://open.douyin.com">https://open.douyin.com</a>
     */
    @Builder.Default
    private String openUrl = "https://open.douyin.com";

    /**
     * 按照抖音、西瓜、头条的顺序赋值  否则会出现取值错位的问题
     */

    @Builder.Default
    private List<String> authApisPrefix =
            Arrays.asList(ApiPlatform.DOU_YIN.getPrefix(),
                    ApiPlatform.XI_GUA.getPrefix(),
                    ApiPlatform.SNS.getPrefix());
}
