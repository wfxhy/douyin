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
 * 刷新refresh_token返回结果
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 11:28
 * @modified mdmbct
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RenewRefreshTokenRes implements DyOpenApiResponse {

    private static final long serialVersionUID = -7162310248737837413L;

    private RenewRefreshTokenResData data;

    private String message;

    private ResponseExtra extra;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class RenewRefreshTokenResData extends DefaultResponseData {

        private static final long serialVersionUID = -5619997481606597606L;

        /**
         * 过期时间，单位（秒）
         */
        @JSONField(name = "expires_in")
        private long expiresIn;

        @JSONField(name = "refresh_token")
        private String refreshToken;
    }
}
