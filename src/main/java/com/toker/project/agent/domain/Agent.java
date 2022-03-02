package com.toker.project.agent.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toker.framework.web.domain.BaseEntity;
import com.toker.project.system.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.TreeEntity;

/**
 * 代理商管理对象 agent
 * 
 * @author wf
 * @date 2022-02-28
 */
public class Agent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 代理商名称 */
    @Excel(name = "代理商名称")
    private String name;

    /** 代理商类型 */


    private Long parentId;
    /** 联系人 */
    @Excel(name = "联系人")
    private String leader;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 创建代理商数量 */
    @Excel(name = "创建代理商数量")
    private Long createAgentNum;

    /** 创建商户数量 */
    @Excel(name = "创建商户数量")
    private Long createMerNum;

    @Excel(name = "代理商类型")
    private Long type;

    /** 生效开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date staTime;

    /** 生效结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 视频数 */
    @Excel(name = "视频数")
    private Long videoCount;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    private String ancestors;

    /** 子部门 */
    private List<Agent> children = new ArrayList<Agent>();

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public List<Agent> getChildren()
    {
        return children;
    }

    public void setChildren(List<Agent> children)
    {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setCreateAgentNum(Long createAgentNum) 
    {
        this.createAgentNum = createAgentNum;
    }

    public Long getCreateAgentNum() 
    {
        return createAgentNum;
    }
    public void setCreateMerNum(Long createMerNum) 
    {
        this.createMerNum = createMerNum;
    }

    public Long getCreateMerNum() 
    {
        return createMerNum;
    }
    public void setStaTime(Date staTime) 
    {
        this.staTime = staTime;
    }

    public Date getStaTime() 
    {
        return staTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setVideoCount(Long videoCount) 
    {
        this.videoCount = videoCount;
    }

    public Long getVideoCount() 
    {
        return videoCount;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("createAgentNum", getCreateAgentNum())
            .append("createMerNum", getCreateMerNum())
            .append("staTime", getStaTime())
            .append("endTime", getEndTime())
            .append("videoCount", getVideoCount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
