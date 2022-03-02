package com.toker.project.douyin.service;

import com.toker.project.douyin.domain.DouyinUserVideo;
import com.toker.project.douyin.domain.DouyinUserVideo;

import java.util.List;

/**
 * 抖音用户转发商户视频记录Service接口
 * 
 * @author huohuzhihui
 * @date 2021-03-26
 */
public interface IDouyinUserVideoService 
{
    /**
     * 查询抖音用户转发商户视频记录
     * 
     * @param id 抖音用户转发商户视频记录ID
     * @return 抖音用户转发商户视频记录
     */
    public DouyinUserVideo selectDouyinUserVideoById(Long id);

    /**
     * 查询抖音用户转发商户视频记录列表
     * 
     * @param douyinUserVideo 抖音用户转发商户视频记录
     * @return 抖音用户转发商户视频记录集合
     */
    public List<DouyinUserVideo> selectDouyinUserVideoList(DouyinUserVideo douyinUserVideo);

    /**
     * 新增抖音用户转发商户视频记录
     * 
     * @param douyinUserVideo 抖音用户转发商户视频记录
     * @return 结果
     */
    public int insertDouyinUserVideo(DouyinUserVideo douyinUserVideo);

    /**
     * 修改抖音用户转发商户视频记录
     * 
     * @param douyinUserVideo 抖音用户转发商户视频记录
     * @return 结果
     */
    public int updateDouyinUserVideo(DouyinUserVideo douyinUserVideo);

    /**
     * 批量删除抖音用户转发商户视频记录
     * 
     * @param ids 需要删除的抖音用户转发商户视频记录ID
     * @return 结果
     */
    public int deleteDouyinUserVideoByIds(Long[] ids);

    /**
     * 删除抖音用户转发商户视频记录信息
     * 
     * @param id 抖音用户转发商户视频记录ID
     * @return 结果
     */
    public int deleteDouyinUserVideoById(Long id);

    /**
     * 根据视频ID查询抖音用户视频转发记录
     * @param videoId
     * @return
     */
    public DouyinUserVideo selectDouyinUserVideoByVideoId(String videoId);

    /**
     *查询视频转发量
     * @return
     */
    public Long selectDouyinUserVideoCount(DouyinUserVideo douyinUserVideo);
}
