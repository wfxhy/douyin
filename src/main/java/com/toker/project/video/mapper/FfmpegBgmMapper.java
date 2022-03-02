package com.toker.project.video.mapper;

import com.toker.project.video.domain.FfmpegBgm;

import java.util.List;

/**
 * 视频背景音乐Mapper接口
 *
 * @author huohuzhihui
 * @date 2021-06-20
 */
public interface FfmpegBgmMapper {
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
     * 删除视频背景音乐
     *
     * @param id 视频背景音乐ID
     * @return 结果
     */
    public int deleteFfmpegBgmById(Long id);

    /**
     * 批量删除视频背景音乐
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfmpegBgmByIds(Long[] ids);


    /**
     * 获取一条随机背景音乐
     *
     * @return
     */
    public FfmpegBgm getRandFfmpegBgm();

}
