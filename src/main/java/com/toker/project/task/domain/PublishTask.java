package com.toker.project.task.domain;

import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import com.toker.project.douyin.domain.DouyinUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 待发布视频的任务对象 publish_task
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
public class PublishTask extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 转发人
     */
    @Excel(name = "转发人")
    private Long userId;

    /**
     * 任务执行状态：0待执行，1执行成功 2执行失败
     */
    @Excel(name = "任务执行状态：0待执行，1执行中，2执行成功，3执行失败")
    private Integer status;

    /**
     * 关联活动
     */
    @Excel(name = "关联活动")
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityTitle;
    /**
     * 商户名称
     */
    private String deptName;
    /**
     * 商户ID
     */
    private String merchantId;
    /**
     * 抖音昵称
     */
    private String nickname;

    /**
     * 用户抖音信息
     */
    private DouyinUser douyinUser;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public DouyinUser getDouyinUser() {
        return douyinUser;
    }

    public void setDouyinUser(DouyinUser douyinUser) {
        this.douyinUser = douyinUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .append("activityId", getActivityId())
                .toString();
    }
}
