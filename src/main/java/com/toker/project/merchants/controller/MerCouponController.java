package com.toker.project.merchants.controller;

import com.toker.common.utils.SecurityUtils;
import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.merchants.domain.MerCoupon;
import com.toker.project.merchants.service.IMerCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券Controller
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/merchant/coupon")
public class MerCouponController extends BaseController {
    @Autowired
    private IMerCouponService merCouponService;

    /**
     * 查询优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerCoupon merCoupon) {
        startPage();
        List<MerCoupon> list = merCouponService.selectMerCouponList(merCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:coupon:export')")
    @Log(title = "优惠券", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerCoupon merCoupon) {
        List<MerCoupon> list = merCouponService.selectMerCouponList(merCoupon);
        ExcelUtil<MerCoupon> util = new ExcelUtil<MerCoupon>(MerCoupon.class);
        return util.exportExcel(list, "coupon");
    }

    /**
     * 获取优惠券详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchants:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(merCouponService.selectMerCouponById(id));
    }

    /**
     * 新增优惠券
     */
    @PreAuthorize("@ss.hasPermi('merchants:coupon:add')")
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerCoupon merCoupon) {
        merCoupon.setCreateBy(SecurityUtils.getUsername());
        return toAjax(merCouponService.insertMerCoupon(merCoupon));
    }

    /**
     * 修改优惠券
     */
    @PreAuthorize("@ss.hasPermi('merchants:coupon:edit')")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerCoupon merCoupon) {
        merCoupon.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(merCouponService.updateMerCoupon(merCoupon));
    }

    /**
     * 下架优惠券
     */
    @PreAuthorize("@ss.hasPermi('merchants:coupon:offilne')")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/offline")
    public AjaxResult offline(Long[] ids) {
        return toAjax(merCouponService.offilneMerCoupon(ids));
    }

    /**
     * 删除优惠券
     */
    @PreAuthorize("@ss.hasPermi('merchants:coupon:remove')")
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(merCouponService.deleteMerCouponByIds(ids));
    }
}
