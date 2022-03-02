package com.toker.project.douyin.domain;

import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 抖音用户对象 douyin_user
 * 
 * @author huohuzhihui
 * @date 2021-03-25
 */
public class DouyinUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** open_id */
    @Excel(name = "open_id")
    private String openId;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 性别: * `0` - 未知 * `1` - 男性 * `2` - 女性	 */
    @Excel(name = "性别: * `0` - 未知 * `1` - 男性 * `2` - 女性	")
    private Integer gender;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** $column.columnComment */
    private String unionId;

    /*所属商户*/
    private Long merchantId;

    /*活动*/
    private Long activityId;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public Integer getGender()
    {
        return gender;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nickname", getNickname())
            .append("openId", getOpenId())
            .append("avatar", getAvatar())
            .append("gender", getGender())
            .append("province", getProvince())
            .append("city", getCity())
            .append("country", getCountry())
            .append("unionId", getUnionId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
