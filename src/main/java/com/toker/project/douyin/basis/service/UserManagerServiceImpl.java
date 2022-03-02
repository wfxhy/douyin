package com.toker.project.douyin.basis.service;

import com.toker.project.douyin.basis.api.UserManager;
import com.toker.project.douyin.basis.response.user.FansCheckRes;
import com.toker.project.douyin.basis.response.user.FansListRes;
import com.toker.project.douyin.basis.response.user.FollowingListRes;
import com.toker.project.douyin.basis.response.user.UserOpenInfoRes;
import com.toker.project.douyin.basis.service.interfaces.UserManagerService;
import com.toker.project.douyin.common.bean.PageCount;
import com.toker.project.douyin.common.enums.ApiPlatform;
import com.toker.project.douyin.common.exception.ApiCallException;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.service.BaseDyService;
import com.toker.project.douyin.common.storage.DyStorageManager;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 11:31
 * @modified mdmbct
 * @since 1.0
 */
public class UserManagerServiceImpl extends BaseDyService implements UserManagerService {

    public UserManagerServiceImpl(DyStorageManager storageManager, HttpExecutor httpExecutor) {
        super(storageManager, httpExecutor);
    }

    @Override
    public UserOpenInfoRes.GetUserOpenInfoResData getUserOpenInfo(String openId, ApiPlatform apiPlatform) throws ApiCallException {
        return simpleGetReq(UserManager.OAUTH_USER_OPEN_INFO, openId, apiPlatform, UserOpenInfoRes.GetUserOpenInfoResData.class, openId);
    }

    @Override
    public FansListRes.GetFansListResData getFansList(String openId, long cursor, PageCount count) throws ApiCallException {
        return simpleGetReqForDy(UserManager.FANS_LIST, openId, FansListRes.GetFansListResData.class, openId, cursor, count.getValue());
    }

    @Override
    public FollowingListRes.GetFollowingListResData getFollowingList(String openId, long cursor, PageCount count) throws ApiCallException {
        return simpleGetReqForDy(UserManager.FOLLOWING_LIST, openId, FollowingListRes.GetFollowingListResData.class, openId, cursor, count.getValue());
    }

    @Override
    public FansCheckRes.FansCheckResData fansCheck(String openId, String followerOpenId) throws ApiCallException {
        return simpleGetReqForDy(UserManager.FANS_CHECK, openId, FansCheckRes.FansCheckResData.class, openId, followerOpenId);
    }


}
