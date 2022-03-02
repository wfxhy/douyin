package com.toker.project.douyin.basis.service.interfaces;

import com.toker.project.douyin.basis.response.user.FansCheckRes;
import com.toker.project.douyin.basis.response.user.FansListRes;
import com.toker.project.douyin.basis.response.user.FollowingListRes;
import com.toker.project.douyin.basis.response.user.UserOpenInfoRes;
import com.toker.project.douyin.common.bean.PageCount;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.exception.ApiCallException;

/**
 * <a href="https://open.douyin.com/platform/doc/6848806527751489550">抖音OpenAPI 用户管理</a>
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 11:13
 * @modified mdmbct
 * @since 1.0
 */
public interface UserManagerService {

    /**
     * 获取用户公开信息
     *
     * @param openId          open id
     * @param apiPlatform 传{@link ApiPlatform#DOU_YIN} 或者 {@link ApiPlatform#SNS}
     * @return 用户公开信息 {@link UserOpenInfoRes.GetUserOpenInfoResData}
     * @throws ApiCallException 接口调用异常
     */
    UserOpenInfoRes.GetUserOpenInfoResData getUserOpenInfo(String openId, ApiPlatform apiPlatform) throws ApiCallException;

    /**
     * 获取用户粉丝数
     *
     * @param openId open id
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @param count  每页数量
     * @return 粉丝信息 {@link FansListRes.GetFansListResData}
     * @throws ApiCallException 接口调用异常
     */
    FansListRes.GetFansListResData getFansList(String openId, long cursor, PageCount count) throws ApiCallException;

    /**
     * 获取用户关注列表
     *
     * @param openId open id
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @param count  每页数量
     * @return 关注列表信息 {@link FollowingListRes.GetFollowingListResData}
     * @throws ApiCallException 接口调用异常
     */
    FollowingListRes.GetFollowingListResData getFollowingList(String openId, long cursor, PageCount count) throws ApiCallException;

    FansCheckRes.FansCheckResData fansCheck(String openId, String followerOpenId) throws ApiCallException;
}
