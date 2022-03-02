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
 * 生成client_token返回结果
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 11:32
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientTokenRes implements DyOpenApiResponse {

    private static final long serialVersionUID = 8542840516366663880L;

    private ClientTokenResData data;

    private String message;

    private ResponseExtra extra;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class ClientTokenResData extends DefaultResponseData {

        private static final long serialVersionUID = -484666998260493225L;

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
    }
}
