package com.toker.project.merchants.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 商户信息对象 merchants
 * 
 * @author wf
 * @date 2022-02-28
 */
public class Merchants extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 商户名称 */
    @Excel(name = "商户名称")
    private String name;

    /** 抖音openid */
    @Excel(name = "抖音openid")
    private String openId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 地理位置id */
    @Excel(name = "地理位置id")
    private String poiId;

    /** 地理位置id */
    @Excel(name = "地理位置id")
    private String poiName;

    /** 小程序id */
    private String microAppId;

    /** 小程序名称 */
    private String microAppTitle;

    /** 小程序的打开路径 */
    private String microAppUrl;

    /** 具体地址，用于将来地图导航 */
    @Excel(name = "具体地址，用于将来地图导航")
    private String address;

    /** 抖音主页地址 */
    @Excel(name = "抖音主页地址")
    private String douyinUrl;

    /** 代理商id */
    @Excel(name = "代理商id")
    private Long agentId;

    private String agentName;

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
    private Integer videoCount;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getStaTime() {
        return staTime;
    }

    public void setStaTime(Date staTime) {
        this.staTime = staTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setPoiId(String poiId) 
    {
        this.poiId = poiId;
    }

    public String getPoiId() 
    {
        return poiId;
    }
    public void setPoiName(String poiName) 
    {
        this.poiName = poiName;
    }

    public String getPoiName() 
    {
        return poiName;
    }
    public void setMicroAppId(String microAppId) 
    {
        this.microAppId = microAppId;
    }

    public String getMicroAppId() 
    {
        return microAppId;
    }
    public void setMicroAppTitle(String microAppTitle) 
    {
        this.microAppTitle = microAppTitle;
    }

    public String getMicroAppTitle() 
    {
        return microAppTitle;
    }
    public void setMicroAppUrl(String microAppUrl) 
    {
        this.microAppUrl = microAppUrl;
    }

    public String getMicroAppUrl() 
    {
        return microAppUrl;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDouyinUrl(String douyinUrl) 
    {
        this.douyinUrl = douyinUrl;
    }

    public String getDouyinUrl() 
    {
        return douyinUrl;
    }
    public void setAgentId(Long agentId) 
    {
        this.agentId = agentId;
    }

    public Long getAgentId() 
    {
        return agentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("openId", getOpenId())
            .append("nickname", getNickname())
            .append("poiId", getPoiId())
            .append("poiName", getPoiName())
            .append("microAppId", getMicroAppId())
            .append("microAppTitle", getMicroAppTitle())
            .append("microAppUrl", getMicroAppUrl())
            .append("address", getAddress())
            .append("douyinUrl", getDouyinUrl())
            .append("agentId", getAgentId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
