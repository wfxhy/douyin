package com.toker.project.douyin.basis.service;


import com.toker.project.douyin.basis.api.SearchManager;
import com.toker.project.douyin.basis.response.video.VideoListRes;
import com.toker.project.douyin.basis.service.interfaces.SearchManagerService;
import com.toker.project.douyin.common.bean.PageCount;
import com.toker.project.douyin.common.http.HttpExecutor;
import com.toker.project.douyin.common.service.BaseDyService;
import com.toker.project.douyin.common.storage.DyStorageManager;

/**
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 11:28
 * @modified mdmbct
 * @since 1.0
 */
public class SearchManagerServiceImpl extends BaseDyService implements SearchManagerService {

    public SearchManagerServiceImpl(DyStorageManager storageManager, HttpExecutor httpExecutor) {
        super(storageManager, httpExecutor);
    }

    @Override
    public VideoListRes.VideoListResData videoSearch(String openId, long cursor, PageCount count, String keyword) {
        return simpleGetReqForDy(SearchManager.VIDEO_SEARCH,
                openId,
                VideoListRes.VideoListResData.class,
                openId, cursor, count.getValue()
        );
    }


}
