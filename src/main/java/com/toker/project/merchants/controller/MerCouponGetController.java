package com.toker.project.merchants.controller;

import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.merchants.domain.MerCouponGet;
import com.toker.project.merchants.service.IMerCouponGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券领取Controller
 *
 * @author huohuzhihui
 * @date 2021-04-05
 */
@RestController
@RequestMapping("/merchant/couponget")
public class MerCouponGetController extends BaseController {
    @Autowired
    private IMerCouponGetService merCouponGetService;

    /**
     * 查询优惠券领取列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:couponget:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerCouponGet merCouponGet) {
        startPage();
        List<MerCouponGet> list = merCouponGetService.selectMerCouponGetList(merCouponGet);
        return getDataTable(list);
    }

    /**
     * 导出优惠券领取列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:couponget:export')")
    @Log(title = "优惠券领取", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MerCouponGet merCouponGet) {
        List<MerCouponGet> list = merCouponGetService.selectMerCouponGetList(merCouponGet);
        ExcelUtil<MerCouponGet> util = new ExcelUtil<MerCouponGet>(MerCouponGet.class);
        return util.exportExcel(list, "couponget");
    }

    /**
     * 获取优惠券领取详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:couponget:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(merCouponGetService.selectMerCouponGetById(id));
    }

    /**
     * 新增优惠券领取
     */
    @PreAuthorize("@ss.hasPermi('merchant:couponget:add')")
    @Log(title = "优惠券领取", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerCouponGet merCouponGet) {
        return toAjax(merCouponGetService.insertMerCouponGet(merCouponGet));
    }

    /**
     * 修改优惠券领取
     */
    @PreAuthorize("@ss.hasPermi('merchant:couponget:edit')")
    @Log(title = "优惠券领取", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerCouponGet merCouponGet) {
        return toAjax(merCouponGetService.updateMerCouponGet(merCouponGet));
    }

    /**
     * 删除优惠券领取
     */
    @PreAuthorize("@ss.hasPermi('merchant:couponget:remove')")
    @Log(title = "优惠券领取", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(merCouponGetService.deleteMerCouponGetByIds(ids));
    }
}
