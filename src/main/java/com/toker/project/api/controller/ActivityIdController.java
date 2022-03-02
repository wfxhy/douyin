package com.toker.project.api.controller;


import com.toker.framework.security.service.TokenService;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.project.merchants.domain.MerCoupon;
import com.toker.project.merchants.domain.MerCouponActivity;
import com.toker.project.merchants.domain.Merchants;
import com.toker.project.merchants.service.IMerCouponActivityService;
import com.toker.project.merchants.service.IMerCouponService;
import com.toker.project.merchants.service.IMerchantsService;
import com.toker.project.system.service.ISysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/activity")
public class ActivityIdController {

    @Autowired
    private IMerCouponActivityService merCouponActivityService;
    @Autowired
    private IMerCouponService merCouponService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IMerchantsService merchantsService;

    /**
     * 查询活动信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getActivity")
    public AjaxResult getActivity(Long id) {
        MerCouponActivity merMerchantActity = this.merCouponActivityService.selectMerCouponActivityById(id);
        Date endDate = merMerchantActity.getEndDate();
        Date now = new Date();
        if (now.compareTo(endDate) > 0) {//活动过期自动下线
            merMerchantActity.setStatus(3L);
            this.merCouponActivityService.updateMerCouponActivity(merMerchantActity);
        }
        //查询优惠券
        MerCoupon merCoupon = this.merCouponService.selectMerCouponById(merMerchantActity.getCouponId());
        merMerchantActity.setMerCoupon(merCoupon);

        //如果是跳转到个人主页，那么去设置个人主页
        String redirectType = merMerchantActity.getRedirectType();
        if ("1".equals(redirectType.trim())) {
            Merchants merchants = this.merchantsService.selectMerchantsById(merMerchantActity.getMerchantId());
            String douyinUrl = merchants.getDouyinUrl();
            if (!StringUtils.isEmpty(douyinUrl)) {
                merMerchantActity.setRemark(merchants.getDouyinUrl());
            }
        }
        return AjaxResult.success("查询活动详情成功", merMerchantActity);
    }
}
