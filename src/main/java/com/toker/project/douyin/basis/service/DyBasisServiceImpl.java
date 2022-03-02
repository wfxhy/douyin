package com.toker.project.douyin.basis.service;

import com.toker.project.douyin.basis.service.interfaces.*;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.service.BaseDyService;
import com.toker.project.douyin.common.storage.DyStorageManager;

/**
 * 抖音基础open api抽象类实现
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/9 11:28
 * @modified mdmbct
 * @since 1.0
 */
public final class DyBasisServiceImpl extends BaseDyService implements DyBasisService {


    private volatile Auth2Service auth2Service;

    private volatile UserManagerService userManagerService;

    private volatile VideoManagerService videoService;

    private volatile SearchManagerService searchManagerService;

    private volatile ToolAbilityService toolAbilityService;


    public DyBasisServiceImpl(DyStorageManager storageManager, HttpExecutor okHttpExecutor) {
        super(storageManager, okHttpExecutor);
    }

    @Override
    public final Auth2Service getAuth2Service() {

        if (auth2Service == null) {
            synchronized (Auth2ServiceImpl.class) {
                if (auth2Service == null) {
                    auth2Service = new Auth2ServiceImpl(storageManager, httpExecutor);
                }
            }
        }
        return auth2Service;
    }

    @Override
    public final UserManagerService getUserManagerService() {
        if (userManagerService == null) {
            synchronized (UserManagerServiceImpl.class) {
                if (userManagerService == null) {
                    userManagerService = new UserManagerServiceImpl(storageManager, httpExecutor);
                }
            }
        }
        return userManagerService;
    }

    @Override
    public final VideoManagerService getVideoService() {
        if (videoService == null) {
            synchronized (VideoManagerServiceImpl.class) {
                if (videoService == null) {
                    videoService = new VideoManagerServiceImpl(storageManager, httpExecutor);
                }
            }
        }
        return videoService;
    }

    @Override
    public final SearchManagerService getSearchManagerService() {
        if (searchManagerService == null) {
            synchronized (SearchManagerServiceImpl.class) {
                if (searchManagerService == null) {
                    searchManagerService = new SearchManagerServiceImpl(storageManager, httpExecutor);
                }
            }
        }
        return searchManagerService;
    }

    @Override
    public final ToolAbilityService getToolAbilityService() {
        if (toolAbilityService == null) {
            synchronized (ToolAbilityServiceImpl.class) {
                if (toolAbilityService == null) {
                    toolAbilityService = new ToolAbilityServiceImpl();
                }
            }
        }
        return toolAbilityService;
    }
}
