package com.toker.project.douyin.service;

import com.toker.project.douyin.domain.DouyinUser;

import java.util.List;

/**
 * 抖音用户Service接口
 * 
 * @author huohuzhihui
 * @date 2021-03-25
 */
public interface IDouyinUserService 
{
    /**
     * 查询抖音用户
     * 
     * @param id 抖音用户ID
     * @return 抖音用户
     */
    public DouyinUser selectDouyinUserById(Long id);

    /**
     * 查询抖音用户列表
     * 
     * @param douyinUser 抖音用户
     * @return 抖音用户集合
     */
    public List<DouyinUser> selectDouyinUserList(DouyinUser douyinUser);

    /**
     * 新增抖音用户
     * 
     * @param douyinUser 抖音用户
     * @return 结果
     */
    public int insertDouyinUser(DouyinUser douyinUser);

    /**
     * 修改抖音用户
     * 
     * @param douyinUser 抖音用户
     * @return 结果
     */
    public int updateDouyinUser(DouyinUser douyinUser);

    /**
     * 批量删除抖音用户
     * 
     * @param ids 需要删除的抖音用户ID
     * @return 结果
     */
    public int deleteDouyinUserByIds(Long[] ids);

    /**
     * 删除抖音用户信息
     * 
     * @param id 抖音用户ID
     * @return 结果
     */
    public int deleteDouyinUserById(Long id);

    /**
     * 根据抖音用户授权的openid获取抖音用户
     * @param openId
     * @return
     */
    public DouyinUser selectDouyinUserByOpenId(String openId);

    public int updateDouyinUserByOpenId(DouyinUser douyinUser1);

    public String getUserAccessToken(String code);

    public DouyinUser saveDouyinUserByCode(String code);


}
