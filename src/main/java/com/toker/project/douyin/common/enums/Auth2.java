package com.toker.project.douyin.common.enums;

import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import com.toker.project.douyin.common.body.VoidBody;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.response.auth.AccessTokenRes;
import com.toker.project.douyin.common.response.auth.ClientTokenRes;
import com.toker.project.douyin.common.response.auth.RenewRefreshTokenRes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 账号授权 open Api
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/8 10:48
 * @modified mdmbct
 * @since 1.0
 */

@Getter
@RequiredArgsConstructor
public enum Auth2 implements DyOpenApi {

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806493387606024">获取access_token</>
     * <p>
     * 注意:<p>
     * access_token 为用户授权第三方接口调用的凭证，存储在客户端，可能会被窃取，泄漏后可能会发生用户隐私数据泄漏的风险，建议存储在服务端。
     */
    OAUTH_ACCESS_TOKEN(RequestMethod.GET,
            VoidBody.class,
            AccessTokenRes.class,
            "/oauth/access_token?client_key=%s&client_secret=%s&code=%s&grant_type=authorization_code"),


    /**
     * <a href="https://open.douyin.com/platform/doc/6848806519174154248">刷新refresh_token</a>
     * <p>
     * Scope: `renew_refresh_token `不需要授权
     * <p>
     * 该接口用于刷新refresh_token的有效期；该接口适用于抖音授权。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 通过旧的refresh_token获取新的refresh_token，调用后旧refresh_token会失效，新refresh_token有30天有效期。最多只能获取5次新的refresh_token，5次过后需要用户重新授权。<p>
     * 使用本接口前提：
     * <p>
     * client_key必须需要具备renew_refresh_token这个权限。
     */
    OAUTH_RENEW_REFRESH_TOKEN(RequestMethod.GET,
            VoidBody.class,
            RenewRefreshTokenRes.class,
            "/oauth/renew_refresh_token?client_key=%s&refresh_token=%s"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806493387573256">生成client_token</a>
     * <p>
     * 该接口用于获取接口调用的凭证client_access_token，主要用于调用不需要用户授权就可以调用的接口；该接口适用于抖音/头条授权。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 头条的OAuth API以https://open.snssdk.com/开头。
     * <p>
     * 西瓜的OAuth API以https://open-api.ixigua.com/开头。
     * <p>
     * client_access_token用于不需要用户授权就可以调用的接口。
     * <p>
     * client_access_token的有效时间为2个小时，重复获取token后会使上次的token失效(但有5分钟的缓冲时间)。
     */
    OAUTH_CLIENT_TOKEN(RequestMethod.GET,
            VoidBody.class,
            ClientTokenRes.class,
            "/oauth/client_token?client_key=%s&client_secret=%s&grant_type=client_credential"),

    /**
     * <a href="https://open.douyin.com/platform/doc/6848806497707722765">刷新access_token</a>
     * <p>
     * 该接口用于刷新access_token的有效期；该接口适用于抖音/头条授权。
     * <p>
     * 注意：
     * <p>
     * 抖音的OAuth API以https://open.douyin.com/开头。
     * <p>
     * 头条的OAuth API以https://open.snssdk.com/开头。
     * <p>
     * 西瓜的OAuth API以https://open-api.ixigua.com/开头。
     * <p>
     * 刷口。
     * <p>新access_token或续期不会改变refresh_token的有效期；如果需要刷新refresh_token有效期可以调用/oauth/renew_refresh_token/接
     * <p>
     * access_token有效期说明：
     * <p>
     * 当access_token过期（过期时间15天）后，可以通过该接口使用refresh_token（过期时间30天）进行刷新：
     * <p>
     * 1. 若access_token已过期，调用接口会报错(error_code=10008或2190008)，refresh_token后会获取一个新的access_token以及新的超时时间。
     * <p>
     * 2. 若access_token未过期，refresh_token不会改变原来的access_token，但超时时间会更新，相当于续期。
     * <p>
     * 3. 若refresh_token过期，获取access_token会报错(error_code=10010)，此时需要重新走用户授权流程。
     */
    OAUTH_REFRESH_TOKEN(RequestMethod.GET,
            VoidBody.class,
            AccessTokenRes.class,
            "/oauth/refresh_token?client_key=%s&grant_type=%s&refresh_token=%s"),
    ;

    private final RequestMethod requestMethod;

    private final Class<? extends DyOpenApiRequestBody> requestBodyClass;

    private final Class<? extends DyOpenApiResponse> responseClass;

    private final String path;

}
