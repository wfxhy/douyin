package com.toker.project.merchants.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toker.framework.aspectj.lang.annotation.Excel;
import com.toker.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 优惠券活动对象 mer_coupon_activity
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
public class MerCouponActivity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 活动主题
     */
    @Excel(name = "活动主题")
    private String title;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 每个人可参与次数：0为不限次
     */
    private Long joinTimes;

    /**
     * 活动类型
     */
    @Excel(name = "活动类型")
    private String actityType;

    /**
     * 延迟发送视频：小时为单位，0为不延迟
     */
    @Excel(name = "延迟发送视频：小时为单位，0为不延迟")
    private Long laterSend;

    /**
     * 活动主图
     */
    private String imgUrl;

    /**
     * 活动状态：0待审核；1进行中；2已下线
     */
    @Excel(name = "活动状态：0待审核；1待上线；2上线中；3已下线")
    private Long status;

    /**
     * 活动宣传视频
     */
    @Excel(name = "活动宣传视频")
    private String videoUrl;

    /**
     * 所属商家
     */
    @Excel(name = "所属商家")
    private Long merchantId;

    private String merchantName;

    /**
     * 优惠券
     */
    @Excel(name = "优惠券")
    private Long couponId;

    /**
     * 抖音话题：title1#话题1 #话题2 @nickname1
     */
    private String douyinTitle;
    /**
     * 视频转发渠道：0抖音，1快手，2视频号，3微信朋友圈
     */
    private Integer forwardChannel;
    /**
     * 视频来源：0按资源分组，1按单个文件，为避免重复导致的抖音限流请按分组选择
     */
    private Integer videoSource;
    /**
     * 多个资源分组
     */
    private String resourceGroupId;

    /**
     * 多个资源分组
     */
    private Long[] resourceGroupIds;

    //视频标题
    private String videoTitle;

    //参与后跳转的页面：参与后跳转类型：0优惠券页面1商家抖音主页
    private String redirectType;

    //活动二维码字符串
    private String qrCodeText;

    //优惠券
    private MerCoupon merCoupon;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setJoinTimes(Long joinTimes) {
        this.joinTimes = joinTimes;
    }

    public Long getJoinTimes() {
        return joinTimes;
    }

    public void setActityType(String actityType) {
        this.actityType = actityType;
    }

    public String getActityType() {
        return actityType;
    }

    public void setLaterSend(Long laterSend) {
        this.laterSend = laterSend;
    }

    public Long getLaterSend() {
        return laterSend;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getDouyinTitle() {
        return douyinTitle;
    }

    public void setDouyinTitle(String douyinTitle) {
        this.douyinTitle = douyinTitle;
    }


    public Integer getForwardChannel() {
        return forwardChannel;
    }

    public void setForwardChannel(Integer forwardChannel) {
        this.forwardChannel = forwardChannel;
    }

    public Integer getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(Integer videoSource) {
        this.videoSource = videoSource;
    }

    public String getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public Long[] getResourceGroupIds() {
        return resourceGroupIds;
    }

    public void setResourceGroupIds(Long[] resourceGroupIds) {
        this.resourceGroupIds = resourceGroupIds;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }


    public String getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(String redirectType) {
        this.redirectType = redirectType;
    }

    public String getQrCodeText() {
        return qrCodeText;
    }

    public void setQrCodeText(String qrCodeText) {
        this.qrCodeText = qrCodeText;
    }

    public MerCoupon getMerCoupon() {
        return merCoupon;
    }

    public void setMerCoupon(MerCoupon merCoupon) {
        this.merCoupon = merCoupon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("description", getDescription())
                .append("beginDate", getBeginDate())
                .append("endDate", getEndDate())
                .append("joinTimes", getJoinTimes())
                .append("actityType", getActityType())
                .append("laterSend", getLaterSend())
                .append("imgUrl", getImgUrl())
                .append("status", getStatus())
                .append("videoUrl", getVideoUrl())
                .append("merchantId", getMerchantId())
                .append("couponId", getCouponId())
                .toString();
    }
}
