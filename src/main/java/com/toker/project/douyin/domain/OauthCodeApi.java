package com.toker.project.douyin.domain;

import com.toker.framework.web.domain.BaseEntity;

/**
 * 业务表 gen_table
 * 
 * @author ruoyi
 */
public class OauthCodeApi extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 应用唯一标识 */
    private String clientKey ;

    /** 授权时填写code */
    private String responseType ;

    /** 应用授权作用域,多个授权作用域以英文逗号（,）分隔 */
    private String scope ;

    /** 授权成功后的回调地址，必须以http/https开头。域名必须对应申请应用时填写的域名 */
    private String redirectUri ;

    /** 应用授权可选作用域,多个授权作用域以英文逗号（,）分隔，每一个授权作用域后需要加上一个是否默认勾选的参数，1为默认勾选，0为默认不勾选，例如friend_relation,1,message,0 */
    private String optionalScope;

    /** 用于保持请求和回调的状态 */
    private String state;

    private String clientSecret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOptionalScope() {
        return optionalScope;
    }

    public void setOptionalScope(String optionalScope) {
        this.optionalScope = optionalScope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "OauthCodeApi{" +
                "id=" + id +
                ", clientKey='" + clientKey + '\'' +
                ", responseType='" + responseType + '\'' +
                ", scope='" + scope + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", optionalScope='" + optionalScope + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}