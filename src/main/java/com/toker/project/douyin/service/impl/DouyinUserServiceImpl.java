package com.toker.project.douyin.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toker.common.utils.DateUtils;
import com.toker.project.douyin.basis.response.user.UserOpenInfoRes;
import com.toker.project.douyin.basis.service.interfaces.DyBasisService;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.response.auth.AccessTokenRes;
import com.toker.project.douyin.domain.DouyinUser;
import com.toker.project.douyin.mapper.DouyinUserMapper;
import com.toker.project.douyin.service.IDouyinUserService;
import com.toker.common.utils.DateUtils;
import com.toker.project.douyin.domain.DouyinUser;
import com.toker.project.douyin.mapper.DouyinUserMapper;
import com.toker.project.douyin.service.IDouyinUserService;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抖音用户Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2021-03-25
 */
@Service
public class DouyinUserServiceImpl implements IDouyinUserService 
{
    @Resource
    private DyBasisService dyBasisService;

    @Autowired
    private DouyinUserMapper douyinUserMapper;

    @Value("${douyin.appId}")
    private String clientKey;

    @Value("${douyin.appSecret}")
    private String clientSecret;

    @Value("${douyin.accessTokenUrl}")
    private String accessTokenUrl;


    /**
     * 查询抖音用户
     * 
     * @param id 抖音用户ID
     * @return 抖音用户
     */
    @Override
    public DouyinUser selectDouyinUserById(Long id)
    {
        return douyinUserMapper.selectDouyinUserById(id);
    }

    /**
     * 查询抖音用户列表
     * 
     * @param douyinUser 抖音用户
     * @return 抖音用户
     */
    @Override
    public List<DouyinUser> selectDouyinUserList(DouyinUser douyinUser)
    {
        return douyinUserMapper.selectDouyinUserList(douyinUser);
    }

    /**
     * 新增抖音用户
     * 
     * @param douyinUser 抖音用户
     * @return 结果
     */
    @Override
    public int insertDouyinUser(DouyinUser douyinUser)
    {
        return douyinUserMapper.insertDouyinUser(douyinUser);
    }

    /**
     * 修改抖音用户
     * 
     * @param douyinUser 抖音用户
     * @return 结果
     */
    @Override
    public int updateDouyinUser(DouyinUser douyinUser)
    {
        douyinUser.setUpdateTime(DateUtils.getNowDate());
        return douyinUserMapper.updateDouyinUser(douyinUser);
    }

    /**
     * 批量删除抖音用户
     * 
     * @param ids 需要删除的抖音用户ID
     * @return 结果
     */
    @Override
    public int deleteDouyinUserByIds(Long[] ids)
    {
        return douyinUserMapper.deleteDouyinUserByIds(ids);
    }

    /**
     * 删除抖音用户信息
     * 
     * @param id 抖音用户ID
     * @return 结果
     */
    @Override
    public int deleteDouyinUserById(Long id)
    {
        return douyinUserMapper.deleteDouyinUserById(id);
    }

    @Override
    public DouyinUser selectDouyinUserByOpenId(String openId) {
        return douyinUserMapper.selectDouyinUserByOpenId(openId);
    }

    @Override
    public int updateDouyinUserByOpenId(DouyinUser douyinUser) {
        douyinUser.setUpdateTime(DateUtils.getNowDate());
        return douyinUserMapper.updateDouyinUserByOpenId(douyinUser);
    }

    @Override
    public String getUserAccessToken(String code) {
        String token_url = "https://open.douyin.com/oauth/access_token/";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(token_url+"?client_key="+clientKey+"&client_secret="+clientSecret+"&code="+code+"&grant_type=authorization_code");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());

            String accessResponseContent = EntityUtils.toString(responseEntity);
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + accessResponseContent);
            }

            Map accessTokenMap = new HashMap();
            try {
                ObjectMapper om = new ObjectMapper();
                accessTokenMap = om.readValue(accessResponseContent, Map.class);
                Map<String, Object> object = (Map<String, Object>) accessTokenMap.get("data");
                Object access_token = object.get("access_token");
                return access_token.toString();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public DouyinUser saveDouyinUserByCode(String code) {
        //查询抖音AccessToken
        AccessTokenRes.AccessTokenResData accessTokenResData = dyBasisService.getAuth2Service().authAccessToken(code, ApiPlatform.DOU_YIN);
        System.out.println("11111111111111++"+accessTokenResData.toString());
        DouyinUser douyinUser = this.douyinUserMapper.selectDouyinUserByOpenId(accessTokenResData.getOpenId());
        if(douyinUser==null){//如果人员不存在那么进行保存
            UserOpenInfoRes.GetUserOpenInfoResData userOpenInfoResData = dyBasisService.getUserManagerService().getUserOpenInfo(accessTokenResData.getOpenId(), ApiPlatform.DOU_YIN);
            System.out.println("2222222222++"+userOpenInfoResData.toString());
            douyinUser = new DouyinUser();
            douyinUser.setOpenId(userOpenInfoResData.getOpenId());
            douyinUser.setUnionId(userOpenInfoResData.getUnionId());
            douyinUser.setAvatar(userOpenInfoResData.getAvatar());
            douyinUser.setCity(userOpenInfoResData.getCity());
            douyinUser.setCountry(userOpenInfoResData.getCountry());
            douyinUser.setGender(userOpenInfoResData.getGender());
            douyinUser.setNickname(userOpenInfoResData.getNickName());
            douyinUser.setProvince(userOpenInfoResData.getProvince());
            douyinUser.setCreateTime(DateUtils.getNowDate());
            this.douyinUserMapper.insertDouyinUser(douyinUser);
        }
        return douyinUser;
    }
}
