package com.toker.project.merchants.controller;

import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.merchants.domain.MerCouponActivity;
import com.toker.project.merchants.service.IMerCouponActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券活动Controller
 *
 * @author huohuzhihui
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/merchant/activity")
public class MerCouponActivityController extends BaseController {
    @Autowired
    private IMerCouponActivityService merCouponActivityService;

    /**
     * 查询优惠券活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MerCouponActivity merCouponActivity) {
        startPage();
        List<MerCouponActivity> list = merCouponActivityService.selectMerCouponActivityList(merCouponActivity);
        return getDataTable(list);
    }

    /**
     * 导出优惠券活动列表
     */
    @Log(title = "优惠券活动", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerCouponActivity merCouponActivity) {
        List<MerCouponActivity> list = merCouponActivityService.selectMerCouponActivityList(merCouponActivity);
        ExcelUtil<MerCouponActivity> util = new ExcelUtil<MerCouponActivity>(MerCouponActivity.class);
        return util.exportExcel(list, "activity");
    }

    /**
     * 获取优惠券活动详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(merCouponActivityService.selectMerCouponActivityById(id));
    }

    /**
     * 新增优惠券活动
     */
    @Log(title = "优惠券活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerCouponActivity merCouponActivity) {
        return toAjax(merCouponActivityService.insertMerCouponActivity(merCouponActivity));
    }

    /**
     * 修改优惠券活动
     */
    @Log(title = "优惠券活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerCouponActivity merCouponActivity) {
        return toAjax(merCouponActivityService.updateMerCouponActivity(merCouponActivity));
    }

    /**
     * 删除优惠券活动
     */
    @Log(title = "优惠券活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(merCouponActivityService.deleteMerCouponActivityByIds(ids));
    }

    /**
     * 下架活动
     */
    @Log(title = "优惠券活动", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/offline")
    public AjaxResult offline(Long[] ids) {
        return toAjax(merCouponActivityService.offilneMerCouponActivity(ids));
    }

    /**
     * 上线活动
     */
    @Log(title = "优惠券活动", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/online")
    public AjaxResult online(Long[] ids) {
        return toAjax(merCouponActivityService.onlineMerCouponActivity(ids));
    }


}
