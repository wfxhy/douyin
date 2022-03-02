package com.toker.project.merchants.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 优惠券对象 mer_coupon
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
public class MerCoupon extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 商户
     */
    @Excel(name = "商户")
    private Long merchantId;

    /**
     * 折扣券标题
     */
    @Excel(name = "折扣券标题")
    private String title;

    /**
     * 折扣
     */
    @Excel(name = "折扣")
    private Integer discount;

    /**
     * 有效期开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 有效期结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date overTime;

    /**
     * 每个用户领券上限，如不填，则默认为1
     */
    @Excel(name = "每个用户领券上限，如不填，则默认为1")
    private Integer collectionTimes;

    /**
     * 封面图片
     */
    @Excel(name = "封面图片")
    private String coverImg;

    /**
     * 封面简介
     */
    @Excel(name = "封面简介")
    private String coverInfo;

    /**
     * 使用须知
     */
    @Excel(name = "使用须知")
    private String useNotice;

    /**
     * 减免金额
     */
    @Excel(name = "减免金额")
    private Long amount;

    /**
     * 最低消费：0代表不限制
     */
    @Excel(name = "最低消费：0代表不限制")
    private Long minCost;

    /**
     * 0：代金券；1：折扣券；2：兑换券
     */
    @Excel(name = "0：代金券；1：折扣券；2：兑换券")
    private Integer type;

    /**
     * 数量：0为无限量
     */
    @Excel(name = "数量：0为无限量")
    private Long quantity;

    /**
     * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     */
    @Excel(name = "商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。")
    private Integer goodsType;

    /**
     * 商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    @Excel(name = "商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。")
    private String goodsValue;

    /**
     * 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；
     */
    @Excel(name = "有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；")
    private Integer timeType;

    /**
     * 基于领取时间的有效天数days。
     */
    @Excel(name = "基于领取时间的有效天数days。")
    private Integer days;

    /**
     * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架
     */
    @Excel(name = "优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架")
    private Integer status;

    /**
     * 商户名称
     */
    private String merchantName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setCollectionTimes(Integer collectionTimes) {
        this.collectionTimes = collectionTimes;
    }

    public Integer getCollectionTimes() {
        return collectionTimes;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverInfo(String coverInfo) {
        this.coverInfo = coverInfo;
    }

    public String getCoverInfo() {
        return coverInfo;
    }

    public void setUseNotice(String useNotice) {
        this.useNotice = useNotice;
    }

    public String getUseNotice() {
        return useNotice;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setMinCost(Long minCost) {
        this.minCost = minCost;
    }

    public Long getMinCost() {
        return minCost;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getGoodsValue() {
        return goodsValue;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getDays() {
        return days;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("merchantId", getMerchantId())
                .append("title", getTitle())
                .append("discount", getDiscount())
                .append("startTime", getStartTime())
                .append("collectionTimes", getCollectionTimes())
                .append("coverImg", getCoverImg())
                .append("coverInfo", getCoverInfo())
                .append("useNotice", getUseNotice())
                .append("amount", getAmount())
                .append("minCost", getMinCost())
                .append("type", getType())
                .append("quantity", getQuantity())
                .append("goodsType", getGoodsType())
                .append("goodsValue", getGoodsValue())
                .append("timeType", getTimeType())
                .append("days", getDays())
                .append("status", getStatus())
                .toString();
    }
}
