package com.toker.project.douyin.basis.service.interfaces;


import com.toker.project.douyin.basis.api.SearchManager;
import com.toker.project.douyin.basis.response.video.VideoListRes;
import com.toker.project.douyin.common.bean.PageCount;

/**
 * 搜索管理api实现接口
 *
 * @author mdmbct  mdmbct@outlook.com
 * @date 2021/3/18 11:26
 * @modified mdmbct
 * @since 1.0
 */
public interface SearchManagerService {

    /**
     * 视频搜索 <p>
     * @see SearchManager#VIDEO_SEARCH
     * @param openId open id
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @param count 每页数量
     * @param keyword 关键词
     * @return 视频列表数据
     */
    public VideoListRes.VideoListResData videoSearch(String openId, long cursor, PageCount count, String keyword);
}
