package com.toker.project.video.service.impl;

import com.toker.common.utils.DateUtils;
import com.toker.project.video.domain.FfmpegBgm;
import com.toker.project.video.mapper.FfmpegBgmMapper;
import com.toker.project.video.service.IFfmpegBgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频背景音乐Service业务层处理
 *
 * @author huohuzhihui
 * @date 2021-06-20
 */
@Service
public class FfmpegBgmServiceImpl implements IFfmpegBgmService {
    @Autowired
    private FfmpegBgmMapper ffmpegBgmMapper;

    /**
     * 查询视频背景音乐
     *
     * @param id 视频背景音乐ID
     * @return 视频背景音乐
     */
    @Override
    public FfmpegBgm selectFfmpegBgmById(Long id) {
        return ffmpegBgmMapper.selectFfmpegBgmById(id);
    }

    /**
     * 查询视频背景音乐列表
     *
     * @param ffmpegBgm 视频背景音乐
     * @return 视频背景音乐
     */
    @Override
    public List<FfmpegBgm> selectFfmpegBgmList(FfmpegBgm ffmpegBgm) {
        return ffmpegBgmMapper.selectFfmpegBgmList(ffmpegBgm);
    }

    /**
     * 新增视频背景音乐
     *
     * @param ffmpegBgm 视频背景音乐
     * @return 结果
     */
    @Override
    public int insertFfmpegBgm(FfmpegBgm ffmpegBgm) {
        ffmpegBgm.setCreateTime(DateUtils.getNowDate());
        return ffmpegBgmMapper.insertFfmpegBgm(ffmpegBgm);
    }

    /**
     * 修改视频背景音乐
     *
     * @param ffmpegBgm 视频背景音乐
     * @return 结果
     */
    @Override
    public int updateFfmpegBgm(FfmpegBgm ffmpegBgm) {
        ffmpegBgm.setUpdateTime(DateUtils.getNowDate());
        return ffmpegBgmMapper.updateFfmpegBgm(ffmpegBgm);
    }

    /**
     * 批量删除视频背景音乐
     *
     * @param ids 需要删除的视频背景音乐ID
     * @return 结果
     */
    @Override
    public int deleteFfmpegBgmByIds(Long[] ids) {
        return ffmpegBgmMapper.deleteFfmpegBgmByIds(ids);
    }

    /**
     * 删除视频背景音乐信息
     *
     * @param id 视频背景音乐ID
     * @return 结果
     */
    @Override
    public int deleteFfmpegBgmById(Long id) {
        return ffmpegBgmMapper.deleteFfmpegBgmById(id);
    }

    @Override
    public FfmpegBgm getRandFfmpegBgm() {
        return ffmpegBgmMapper.getRandFfmpegBgm();
    }
}
