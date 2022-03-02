package com.toker.project.douyin.service.impl;

import com.toker.project.douyin.domain.DouyinUserVideo;
import com.toker.project.douyin.mapper.DouyinUserVideoMapper;
import com.toker.project.douyin.service.IDouyinUserVideoService;
import com.toker.common.utils.DateUtils;
import com.toker.project.douyin.domain.DouyinUserVideo;
import com.toker.project.douyin.mapper.DouyinUserVideoMapper;
import com.toker.project.douyin.service.IDouyinUserVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 抖音用户转发商户视频记录Service业务层处理
 * 
 * @author huohuzhihui
 * @date 2021-03-26
 */
@Service
public class DouyinUserVideoServiceImpl implements IDouyinUserVideoService
{
    @Autowired
    private DouyinUserVideoMapper douyinUserVideoMapper;

    /**
     * 查询抖音用户转发商户视频记录
     * 
     * @param id 抖音用户转发商户视频记录ID
     * @return 抖音用户转发商户视频记录
     */
    @Override
    public DouyinUserVideo selectDouyinUserVideoById(Long id)
    {
        return douyinUserVideoMapper.selectDouyinUserVideoById(id);
    }

    /**
     * 查询抖音用户转发商户视频记录列表
     * 
     * @param douyinUserVideo 抖音用户转发商户视频记录
     * @return 抖音用户转发商户视频记录
     */
    @Override
    public List<DouyinUserVideo> selectDouyinUserVideoList(DouyinUserVideo douyinUserVideo)
    {
        return douyinUserVideoMapper.selectDouyinUserVideoList(douyinUserVideo);
    }

    /**
     * 新增抖音用户转发商户视频记录
     * 
     * @param douyinUserVideo 抖音用户转发商户视频记录
     * @return 结果
     */
    @Override
    public int insertDouyinUserVideo(DouyinUserVideo douyinUserVideo)
    {
        douyinUserVideo.setCreateTime(DateUtils.getNowDate());
        return douyinUserVideoMapper.insertDouyinUserVideo(douyinUserVideo);
    }

    /**
     * 修改抖音用户转发商户视频记录
     * 
     * @param douyinUserVideo 抖音用户转发商户视频记录
     * @return 结果
     */
    @Override
    public int updateDouyinUserVideo(DouyinUserVideo douyinUserVideo)
    {
        return douyinUserVideoMapper.updateDouyinUserVideo(douyinUserVideo);
    }

    /**
     * 批量删除抖音用户转发商户视频记录
     * 
     * @param ids 需要删除的抖音用户转发商户视频记录ID
     * @return 结果
     */
    @Override
    public int deleteDouyinUserVideoByIds(Long[] ids)
    {
        return douyinUserVideoMapper.deleteDouyinUserVideoByIds(ids);
    }

    /**
     * 删除抖音用户转发商户视频记录信息
     * 
     * @param id 抖音用户转发商户视频记录ID
     * @return 结果
     */
    @Override
    public int deleteDouyinUserVideoById(Long id)
    {
        return douyinUserVideoMapper.deleteDouyinUserVideoById(id);
    }

    @Override
    public DouyinUserVideo selectDouyinUserVideoByVideoId(String videoId) {
        return douyinUserVideoMapper.selectDouyinUserVideoByVideoId(videoId);
    }

    @Override
    public Long selectDouyinUserVideoCount(DouyinUserVideo douyinUserVideo) {
        return douyinUserVideoMapper.selectDouyinUserVideoCount(douyinUserVideo);
    }
}
