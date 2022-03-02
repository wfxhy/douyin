package com.toker.project.video.domain;

import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 视频背景音乐对象 ffmpeg_bgm
 *
 * @author huohuzhihui
 * @date 2021-06-20
 */
public class FfmpegBgm extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String audioUrl;

    /**
     * 0:启用；1：停用
     */
    @Excel(name = "0:启用；1：停用")
    private Integer status;

    /**
     * 时长，单位为秒
     */
    @Excel(name = "时长，单位为秒")
    private Long duration;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("audioUrl", getAudioUrl())
                .append("status", getStatus())
                .append("duration", getDuration())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
