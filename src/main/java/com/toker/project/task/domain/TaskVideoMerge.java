package com.toker.project.task.domain;

import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 视频合成记录对象 task_video_merge
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
public class TaskVideoMerge extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 所属任务
     */
    @Excel(name = "所属任务")
    private Long taskId;

    /**
     * 所属商户
     */
    private Long merchantId;

    /**
     * 合并的资源片段
     */
    @Excel(name = "合并的资源片段")
    private String resourceIds;

    /**
     * 合成状态:0合成成功1合成失败
     */
    @Excel(name = "合成状态:0合成成功1合成失败")
    private Integer status;

    /**
     * 失败详情
     */
    @Excel(name = "失败详情")
    private String errorMsg;

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

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taskId", getTaskId())
                .append("resourceIds", getResourceIds())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("errorMsg", getErrorMsg())
                .toString();
    }
}
