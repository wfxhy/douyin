package com.toker.project.merchants.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.toker.common.utils.SecurityUtils;
import com.toker.project.api.service.ApiService;
import com.toker.project.douyin.basis.response.video.VideoPoiRes;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.project.merchants.domain.Merchants;
import com.toker.project.merchants.service.IMerchantsService;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.web.page.TableDataInfo;

/**
 * 商户信息Controller
 * 
 * @author wf
 * @date 2022-02-28
 */
@RestController
@RequestMapping("/merchants/merchants")
public class MerchantsController extends BaseController
{
    @Autowired
    private IMerchantsService merchantsService;

    @Autowired
    private ApiService apiService;

    @GetMapping("/getMerchantsList")
    public AjaxResult getMerchantsList(Merchants merchants)
    {
        merchants.setAgentId(SecurityUtils.getLoginUser().getUser().getAgentId());
        List<Merchants> list = merchantsService.selectMerchantsList(merchants);
        return AjaxResult.success(list);
    }

    /**
     * 查询商户信息列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:merchants:list')")
    @GetMapping("/list")
    public TableDataInfo list(Merchants merchants)
    {
        startPage();
        merchants.setAgentId(SecurityUtils.getLoginUser().getUser().getAgentId());
        List<Merchants> list = merchantsService.selectMerchantsList(merchants);
        return getDataTable(list);
    }

    /**
     * 导出商户信息列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:merchants:export')")
    @Log(title = "商户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Merchants merchants)
    {
        List<Merchants> list = merchantsService.selectMerchantsList(merchants);
        ExcelUtil<Merchants> util = new ExcelUtil<Merchants>(Merchants.class);
        util.exportExcel(response, list, "商户信息数据");
    }

    /**
     * 获取商户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchants:merchants:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merchantsService.selectMerchantsById(id));
    }

    /**
     * 新增商户信息
     */
    @PreAuthorize("@ss.hasPermi('merchants:merchants:add')")
    @Log(title = "商户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Merchants merchants)
    {
        return toAjax(merchantsService.insertMerchants(merchants));
    }

    /**
     * 修改商户信息
     */
    @PreAuthorize("@ss.hasPermi('merchants:merchants:edit')")
    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Merchants merchants)
    {
        return toAjax(merchantsService.updateMerchants(merchants));
    }

    /**
     * 删除商户信息
     */
    @PreAuthorize("@ss.hasPermi('merchants:merchants:remove')")
    @Log(title = "商户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merchantsService.deleteMerchantsByIds(ids));
    }

    @GetMapping("/searchPOI/{address}")
    @ResponseBody
    public AjaxResult searchPOI(@PathVariable String address) {
        List<VideoPoiRes.Poi> poi = apiService.searchPOI(address);
        return AjaxResult.success("查询POI地点信息成功", poi);
    }
}
