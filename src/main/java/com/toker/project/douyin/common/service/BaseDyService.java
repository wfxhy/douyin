package com.toker.project.douyin.common.service;

import com.alibaba.fastjson.JSONObject;
import com.toker.project.douyin.common.bean.AccessToken;
import com.toker.project.douyin.common.bean.RefreshToken;
import com.toker.project.douyin.common.body.DyOpenApiRequestBody;
import com.toker.project.douyin.common.constant.RequestParameterName;
import com.toker.project.douyin.common.enums.*;
import com.toker.project.douyin.common.exception.*;
import com.toker.project.douyin.common.http.Head;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.response.DefaultResponseData;
import com.toker.project.douyin.common.response.DyOpenApiResponse;
import com.toker.project.douyin.common.storage.DyStorageManager;
import com.toker.project.douyin.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * DyService抽象类
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 9:45
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
public abstract class BaseDyService implements DyService {


    protected final DyStorageManager storageManager;

    protected final HttpExecutor httpExecutor;

    protected final AuthService authService;

    public BaseDyService(DyStorageManager storageManager, HttpExecutor httpExecutor) {
        this.storageManager = storageManager;
        this.httpExecutor = httpExecutor;
        this.authService = new AuthService(storageManager, httpExecutor);
    }

    @Override
    public DyStorageManager getDyStorageManager() {
        return storageManager;
    }

    @Override
    public HttpExecutor getHttpExecutor() {
        return httpExecutor;
    }

    @Override
    public AccessToken getAccessToken(String openId, ApiPlatform apiPlatform, boolean autoRenew) throws LandExpirationException {

        AccessToken accessToken = storageManager.getAccessToken(openId);
        if (accessToken != null) {
            return accessToken;
        }
        if (autoRenew) {
            // 刷新AccessToken需要RefreshToken
            // 如果RefreshToken不存在的话 getRefreshToken中会抛出LandExpirationException 需要重新走授权的流程
            RefreshToken refreshToken = getRefreshToken(openId);
            // 拿RefreshToken获取AccessToken
            return authService.renewAccessToken(refreshToken, apiPlatform);
        }
        return null;
    }

    @Override
    public RefreshToken getRefreshToken(String openId) throws LandExpirationException {
        RefreshToken refreshToken = storageManager.getRefreshToken(openId);
        if (refreshToken == null) {
            log.error(LandExpirationException.DEFAULT_MSG);
            throw new LandExpirationException();
        }
        return refreshToken;
    }



    @Override
    public String getRequest(String openId, String url, ApiPlatform apiPlatform, Head... heads) throws ApiCallException, InvalidRequestParamException {

//        if (url.contains(RequestParameterName.ACCESS_TOKEN)) {
//            throw new InvalidRequestParamException("请求的url中不能包含" + RequestParameterName.ACCESS_TOKEN);
//        }

        // 获取AccessToken 自动刷新 不会返回null
        AccessToken accessToken = getAccessToken(openId, apiPlatform, true);
        // 拼接上access_token参数
        String competedUrl = completeUrl(url, accessToken.getValue());
        return httpExecutor.executeGet(heads, competedUrl);
    }

    @Override
    public <T> String postRequest(String url, T body, String openId, ApiPlatform apiPlatform, RequestBodyType requestBodyType, Head... heads) throws ApiCallException, InvalidRequestParamException {

        AccessToken accessToken = getAccessToken(openId, apiPlatform, true);
        String competedUrl = completeUrl(url, accessToken.getValue());
        return httpExecutor.executePost(heads, competedUrl, body, requestBodyType);
    }


    /////////////////////////////////////////// private protected method ///////////////////////////////////////////


    protected <T extends DyOpenApiResponse> T handleApiResponse(String responseResult, Class<T> responseClass) {
        return JSONObject.parseObject(responseResult, responseClass);
    }

    protected <T extends DyOpenApiResponse, D extends DefaultResponseData> D handleApiResponse(String responseResult,
                                                                                               Class<T> responseClass,
                                                                                               Class<D> responseDataClass) throws ApiResponseFailedException {
        T t = JSONObject.parseObject(responseResult, responseClass);

        if (t.getData().getErrorCode() == ErrorCode.SUCCESS.getValue()) {
            return (D) t.getData();
        }

        throw new ApiResponseFailedException(t.getErrorMsg());
    }

    /**
     * 补全参数access_token
     *
     * @param originalUrl 未补全的url
     * @param accessToken access token的值
     * @return 补全之后的url
     */
    private String completeUrl(String originalUrl, String accessToken) {

        StringBuilder urlBuilder = new StringBuilder(originalUrl);
        if (originalUrl.endsWith(RequestParameterName.PARAM_AND)) {
            return urlBuilder.append(RequestParameterName.ACCESS_TOKEN).append("=").append(accessToken).toString();
        } else {
            return urlBuilder.append(RequestParameterName.PARAM_AND).append(RequestParameterName.ACCESS_TOKEN).append("=").append(accessToken).toString();
        }
    }

    /**
     * 依据API格式化url
     *
     * @param api      api
     * @param platform api所属平台
     * @param params   api参数
     * @return 格式化之后的api 请求url
     */
    protected String formatRequestUrl(DyOpenApi api, ApiPlatform platform, Object... params) {

        List<Object> paramsEncoded = new ArrayList<>(params.length);

        // 对参数中的String进行utf-8编码
        for (Object param : params) {
            if (param instanceof String) {
                paramsEncoded.add(StringUtils.utf8Encode((String) param));
            } else {
                paramsEncoded.add(param);
            }
        }
        return String.format(api.getUrl(storageManager, platform), paramsEncoded.toArray(new Object[params.length]));
    }

    /**
     * 由api直接发送无请求头的get请求 并直接返回数据
     *
     * @param api               api
     * @param openId            open id
     * @param platform          api平台
     * @param responseDataClass 响应的data字段类型
     * @param urlParams         请求参数
     * @param <D>               响应的data字段类型
     * @return 响应的data字段实例
     */
    protected <D extends DefaultResponseData> D simpleGetReq(
            DyOpenApi api, String openId, ApiPlatform platform,
            Class<D> responseDataClass, Object... urlParams) {

        String url = formatRequestUrl(api, platform, urlParams);
        String request = getRequest(openId, url, platform, null);
        return handleApiResponse(request, api.getResponseClass(), responseDataClass);
    }


    protected <D extends DefaultResponseData> D simpleClientTokenGetReq(
            DyOpenApi api, String clientToken, ApiPlatform platform,
            Class<D> responseDataClass, Object... urlParams) {

        String url = formatRequestUrl(api, platform, urlParams);
        String competedUrl = completeUrl(url, clientToken);
        String request = httpExecutor.executeGet(null, competedUrl);
        return handleApiResponse(request, api.getResponseClass(), responseDataClass);
    }




    protected <D extends DefaultResponseData> D simpleGetReqForDy(DyOpenApi api, String openId, Class<D> responseDataClass, Object... urlParams) {
        return simpleGetReq(api, openId, ApiPlatform.DOU_YIN, responseDataClass, urlParams);
    }

    protected <D extends DefaultResponseData> D jsonPostReq(DyOpenApi api, String openId, ApiPlatform platform, DyOpenApiRequestBody body, Class<D> responseDataClass, Object... urlParams) {
        String url = formatRequestUrl(api, platform, urlParams);
        String result = postRequest(url, body, openId, platform, RequestBodyType.JSON, Head.APPLICATION_JSON);
        return handleApiResponse(result, api.getResponseClass(), responseDataClass);
    }

    /**
     * 抖音平台接口的Application/json post 请求
     *
     * @param api               api
     * @param openId            openid
     * @param body              q请求体
     * @param responseDataClass 接口返回数据中data字段类型
     * @param urlParams         请求的url参数
     * @param <D>               接口返回数据中data字段类型
     * @return 接口返回数据中data字段值
     */
    protected <D extends DefaultResponseData> D jsonPostReqForDy(DyOpenApi api, String openId, DyOpenApiRequestBody body, Class<D> responseDataClass, Object... urlParams) {
        String url = formatRequestUrl(api, ApiPlatform.DOU_YIN, urlParams);
        String result = postRequest(url, body, openId, ApiPlatform.DOU_YIN, RequestBodyType.JSON, Head.APPLICATION_JSON);
        return handleApiResponse(result, api.getResponseClass(), responseDataClass);
    }


}
