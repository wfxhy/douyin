package com.toker.project.douyin.common.response.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.toker.project.douyin.common.response.DefaultResponseData;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.ResponseExtra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 获取ACCESS_TOKEN返回结果
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 11:24
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -7512363004192466970L;

    private AccessTokenResData data;

    private String message;

    private ResponseExtra extra;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class AccessTokenResData extends DefaultResponseData {

        private static final long serialVersionUID = -5731861761058891204L;
        /**
         * 用户刷新access_token
         */
        @JSONField(name = "refresh_token")
        private String refreshToken;

        /**
         * 用户授权的作用域(Scope)，使用逗号（,）分隔，开放平台几乎几乎每个接口都需要特定的Scope。
         */
        private String scope;

        /**
         * 接口调用凭证
         */
        @JSONField(name = "access_token")
        private String accessToken;

        /**
         * access_token接口调用凭证超时时间，单位（秒)
         */
        @JSONField(name = "expires_in")
        private long expiresIn;

        /**
         * 授权用户唯一标识
         */
        @JSONField(name = "open_id")
        private String openId;

        /**
         * refresh_token凭证超时时间，单位（秒)
         */
        @JSONField(name = "refresh_expires_in")
        private long refreshExpiresIn;
    }
}
