package com.toker.project.merchants.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;

/**
 * 商户资源对象 mer_resource
 * 
 * @author wf
 * @date 2022-02-28
 */
public class MerResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 所属商户 */
    @Excel(name = "所属商户")
    private Long merchantId;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String name;

    /** 资源分组 */
    @Excel(name = "资源分组")
    private Long groupId;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private Integer resourceType;

    /** 资源地址 */
    @Excel(name = "资源地址")
    private String resourceUrl;

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setResourceType(Integer resourceType) 
    {
        this.resourceType = resourceType;
    }

    public Integer getResourceType() 
    {
        return resourceType;
    }
    public void setResourceUrl(String resourceUrl) 
    {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceUrl() 
    {
        return resourceUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantId", getMerchantId())
            .append("name", getName())
            .append("groupId", getGroupId())
            .append("resourceType", getResourceType())
            .append("resourceUrl", getResourceUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
