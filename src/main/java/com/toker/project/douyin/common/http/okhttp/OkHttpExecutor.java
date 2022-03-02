package com.toker.project.douyin.common.http.okhttp;


import com.alibaba.fastjson.JSONObject;
import com.toker.project.douyin.common.enums.HttpClientType;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.enums.HttpClientType;
import com.toker.project.douyin.common.enums.RequestBodyType;
import com.toker.project.douyin.common.exception.ApiRequestFailedException;
import com.toker.project.douyin.common.exception.InvalidRequestParamException;
import com.toker.project.douyin.common.http.ByteRequestBody;
import com.toker.project.douyin.common.http.FileRequestBody;
import com.toker.project.douyin.common.http.Head;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * OkHttp实现
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/10 11:15
 * @modified mdmbct
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class OkHttpExecutor implements HttpExecutor {

    private final OkHttpClient client;

    @Override
    public HttpClientType getHttpType() {
        return HttpClientType.OK_HTTP;
    }

    @Override
    public String executeGet(Head[] heads, String url) throws InvalidRequestParamException, ApiRequestFailedException {

        if (StringUtils.isEmpty(url)) {
            throw new InvalidRequestParamException("请求url为空");
        }

        Request.Builder builder = new Request.Builder().url(url).get();

        if (heads != null && heads.length > 0) {
            Headers headers = customHeadToHeads(heads);
            builder.headers(headers);
        }

        return executeRequest(builder.build());
    }

    @Override
    public <D> String executePost(Head[] heads, String url, D data, RequestBodyType requestBodyType) throws InvalidRequestParamException, ApiRequestFailedException {

        if (StringUtils.isEmpty(url)) {
            throw new InvalidRequestParamException("请求url为空");
        }

        RequestBody requestBody;

        if (requestBodyType == RequestBodyType.JSON) {
            requestBody = objectToJsonRequestBody(data);
        } else if (data instanceof FileRequestBody) {
            requestBody = fileRequestBodyToFormRequestBody((FileRequestBody) data);
        } else if (data instanceof ByteRequestBody) {
            requestBody = byteRequestBodyToFormRequestBody((ByteRequestBody) data);
        } else {
            requestBody = objectToFormRequestBody(data);
        }

        Request.Builder builder = new Request.Builder().url(url).post(requestBody);

        if (heads != null && heads.length > 0) {
            Headers headers = customHeadToHeads(heads);
            builder.headers(headers);
        }


        return executeRequest(builder.build());
    }


    private Headers customHeadToHeads(Head[] heads) {

        Headers.Builder builder = new Headers.Builder();
        for (Head head : heads) {
            builder.add(head.getName(), head.getValue());
        }
        return builder.build();
    }

    private <D> RequestBody objectToJsonRequestBody(D o) throws InvalidRequestParamException {

        if (o instanceof String || o instanceof Character) {
            throw new InvalidRequestParamException("content-type为application/json时,请求体不能为字符(串)类型");
        }

        MediaType jsonMt = MediaType.parse("application/json;charset=utf-8");
        return RequestBody.create(jsonMt, JSONObject.toJSONString(o));
    }

    private RequestBody objectToFormRequestBody(Object o) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                builder.addFormDataPart(field.getName(), String.valueOf(field.get(o)));
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        }
        return builder.build();
    }

    private RequestBody fileRequestBodyToFormRequestBody(FileRequestBody body) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(body.getName(),
                        body.getFileName(),
                        RequestBody.create(MediaType.parse(body.getMediaType()), body.getFile())
                );
        return builder.build();

    }

    private RequestBody byteRequestBodyToFormRequestBody(ByteRequestBody body) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(body.getName(),
                        body.getFileName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"), body.getBytes())
                );
        return builder.build();

    }


    private String executeRequest(Request request) {

        log.info("request: " + request);

        Response response;
        String result;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new ApiRequestFailedException(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ApiRequestFailedException(e.getMessage());
        }

        log.info("response: " + result);
        return result;
    }

}
