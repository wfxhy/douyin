package com.toker.project.api.service;

import com.toker.project.douyin.basis.response.video.VideoListRes;
import com.toker.project.douyin.basis.response.video.VideoPoiRes;
import com.toker.project.douyin.domain.DouyinUser;
import com.toker.project.merchants.domain.MerCouponActivity;

import java.util.List;

public interface ApiService {

    /**
     * 合并活动视频
     * @param merCouponActivity
     * @return
     */
    public String mergeActivityVideo(MerCouponActivity merCouponActivity);

    /**
     * 推送活动视频到抖音
     * @param merCouponActivity
     * @param openId
     */
    public void pushVideo(MerCouponActivity merCouponActivity, String openId);


    /**
     * 推送活动视频到抖音
     * @param address
     */
    public List<VideoPoiRes.Poi> searchPOI(String address);


    /**
     * 查询已发布视频的相关数据：点赞数, 播放数等
     * @param openId
     * @param videoId
     * @return
     */
    public VideoListRes.VideoListResData getVideoData(String openId, String videoId);

    public DouyinUser queryDouyinUserByCode(String code);
}
