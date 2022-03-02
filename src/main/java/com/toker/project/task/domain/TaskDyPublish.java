package com.toker.project.task.domain;

import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 抖音推送记录对象 task_dy_publish
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
public class TaskDyPublish extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long taskId;

    /**
     * 抖音视频id
     */
    @Excel(name = "抖音视频id")
    private String itemId;

    /**
     * 抖音openid
     */
    private String openId;

    /**
     * 转发状态：1发布成功；2发布失败
     */
    @Excel(name = "转发状态：1发布成功；2发布失败")
    private Long status;

    /**
     * 活动
     */
    @Excel(name = "活动")
    private Long activityId;

    /**
     * 视频地址
     */
    @Excel(name = "视频地址")
    private String videoPath;

    /**
     * 点赞数
     */
    @Excel(name = "点赞数")
    private Integer diggCount;

    /**
     * 下载数
     */
    @Excel(name = "下载数")
    private Integer downloadCount;

    /**
     * 转发数
     */
    @Excel(name = "转发数")
    private Integer forwardCount;

    /**
     * 播放数
     */
    @Excel(name = "播放数")
    private Integer playCount;

    /**
     * 分享数
     */
    @Excel(name = "分享数")
    private Integer shareCount;

    /**
     * 评论数
     */
    @Excel(name = "评论数")
    private Integer commentCount;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setDiggCount(Integer diggCount) {
        this.diggCount = diggCount;
    }

    public Integer getDiggCount() {
        return diggCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }

    public Integer getForwardCount() {
        return forwardCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taskId", getTaskId())
                .append("createTime", getCreateTime())
                .append("itemId", getItemId())
                .append("status", getStatus())
                .append("activityId", getActivityId())
                .append("videoPath", getVideoPath())
                .append("diggCount", getDiggCount())
                .append("downloadCount", getDownloadCount())
                .append("forwardCount", getForwardCount())
                .append("playCount", getPlayCount())
                .append("shareCount", getShareCount())
                .append("commentCount", getCommentCount())
                .toString();
    }
}
