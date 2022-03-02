package com.toker.project.video.service;

import com.toker.project.video.domain.FfmpegBgm;

import java.util.List;

/**
 * 视频背景音乐Service接口
 *
 * @author huohuzhihui
 * @date 2021-06-20
 */
public interface IFfmpegBgmService {
    /**
     * 查询视频背景音乐
     *
     * @param id 视频背景音乐ID
     * @return 视频背景音乐
     */
    public FfmpegBgm selectFfmpegBgmById(Long id);

    /**
     * 查询视频背景音乐列表
     *
     * @param ffmpegBgm 视频背景音乐
     * @return 视频背景音乐集合
     */
    public List<FfmpegBgm> selectFfmpegBgmList(FfmpegBgm ffmpegBgm);

    /**
     * 新增视频背景音乐
     *
     * @param ffmpegBgm 视频背景音乐
     * @return 结果
     */
    public int insertFfmpegBgm(FfmpegBgm ffmpegBgm);

    /**
     * 修改视频背景音乐
     *
     * @param ffmpegBgm 视频背景音乐
     * @return 结果
     */
    public int updateFfmpegBgm(FfmpegBgm ffmpegBgm);

    /**
     * 批量删除视频背景音乐
     *
     * @param ids 需要删除的视频背景音乐ID
     * @return 结果
     */
    public int deleteFfmpegBgmByIds(Long[] ids);

    /**
     * 删除视频背景音乐信息
     *
     * @param id 视频背景音乐ID
     * @return 结果
     */
    public int deleteFfmpegBgmById(Long id);

    /**
     * 获取随机一条背景音乐
     *
     * @return
     */
    FfmpegBgm getRandFfmpegBgm();
}
