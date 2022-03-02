package com.toker.project.merchants.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 优惠券领取对象 mer_coupon_get
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
public class MerCouponGet extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * $column.columnComment
     */
    @Excel(name = "优惠券ID", readConverterExp = "$column.readConverterExp()")
    private Long couponId;

    /**
     * $column.columnComment
     */
    @Excel(name = "领券人ID", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /**
     * $column.columnComment
     */
    @Excel(name = "券码", readConverterExp = "$column.readConverterExp()")
    private String couponCode;

    /**
     * 领取人昵称
     */
    @Excel(name = "领取人昵称")
    private String userNickname;

    /**
     * 获取类型：0-&gt;后台赠送；1-&gt;主动获取
     */
    @Excel(name = "获取类型：0-&gt;后台赠送；1-&gt;主动获取")
    private Integer getType;

    /**
     * 使用状态：0-&gt;未使用；1-&gt;已使用；2-&gt;已过期
     */
    @Excel(name = "使用状态：0-&gt;未使用；1-&gt;已使用；2-&gt;已过期")
    private Integer useStatus;

    /**
     * 使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date useTime;
    /**
     * 生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    /**
     * 到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 优惠券类型
     */
    private String type;
    /**
     * 优惠券类型
     */
    private String amount;
    /**
     * 最低消费
     */
    private String minCost;
    /**
     * 时间类型
     */
    private String timeType;
    /**
     * 有效天数
     */
    private String days;
    /**
     * 折扣
     */
    private String discount;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 优惠券名称
     */
    private String title;
    /**
     * 领取人昵称
     */
    private String nickname;

    private Long merchantId;
    private Long activityId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setGetType(Integer getType) {
        this.getType = getType;
    }

    public Integer getGetType() {
        return getType;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMinCost() {
        return minCost;
    }

    public void setMinCost(String minCost) {
        this.minCost = minCost;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("couponId", getCouponId())
                .append("userId", getUserId())
                .append("couponCode", getCouponCode())
                .append("userNickname", getUserNickname())
                .append("getType", getGetType())
                .append("createTime", getCreateTime())
                .append("useStatus", getUseStatus())
                .append("useTime", getUseTime())
                .toString();
    }
}
