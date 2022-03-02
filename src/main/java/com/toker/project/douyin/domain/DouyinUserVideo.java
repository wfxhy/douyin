package com.toker.project.douyin.domain;

import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 抖音用户转发商户视频记录对象 douyin_user_video
 * 
 * @author huohuzhihui
 * @date 2021-03-26
 */
public class DouyinUserVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 抖音用户ID */
    @Excel(name = "抖音用户ID")
    private Long douyinUserId;

    /** 抖音视频id */
    @Excel(name = "抖音视频id")
    private String itemId;

    /** 转发状态：1发布成功；2发布失败 */
    @Excel(name = "转发状态：1发布成功；2发布失败")
    private Long status;

    /** 错误码：0代表成功 */
    @Excel(name = "错误码：0代表成功")
    private Integer errorCode;

    /** 错误码描述 */
    @Excel(name = "错误码描述")
    private String description;

    /**所属商户*/
    private Long merchantId;
    /**所属活动*/
    private Long activityId;
    /**所属活动名称*/
    private String activityTitle;

    /**用户昵称*/
    private String nickname;

    /**转发的视频*/
    private String videoPath;

    private DouyinUser douyinUser;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDouyinUserId(Long douyinUserId) 
    {
        this.douyinUserId = douyinUserId;
    }

    public Long getDouyinUserId() 
    {
        return douyinUserId;
    }
    public void setItemId(String itemId) 
    {
        this.itemId = itemId;
    }

    public String getItemId() 
    {
        return itemId;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode()
    {
        return errorCode;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public DouyinUser getDouyinUser() {
        return douyinUser;
    }

    public void setDouyinUser(DouyinUser douyinUser) {
        this.douyinUser = douyinUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("douyinUserId", getDouyinUserId())
            .append("itemId", getItemId())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("errorCode", getErrorCode())
            .append("description", getDescription())
            .toString();
    }
}
