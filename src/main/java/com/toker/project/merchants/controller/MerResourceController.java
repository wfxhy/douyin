package com.toker.project.merchants.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.project.merchants.domain.MerResource;
import com.toker.project.merchants.service.IMerResourceService;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.web.page.TableDataInfo;

/**
 * 商户资源Controller
 * 
 * @author wf
 * @date 2022-02-28
 */
@RestController
@RequestMapping("/merchants/resource")
public class MerResourceController extends BaseController
{
    @Autowired
    private IMerResourceService merResourceService;

    /**
     * 查询商户资源列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerResource merResource)
    {
        startPage();
        List<MerResource> list = merResourceService.selectMerResourceList(merResource);
        return getDataTable(list);
    }

    /**
     * 导出商户资源列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:resource:export')")
    @Log(title = "商户资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MerResource merResource)
    {
        List<MerResource> list = merResourceService.selectMerResourceList(merResource);
        ExcelUtil<MerResource> util = new ExcelUtil<MerResource>(MerResource.class);
        util.exportExcel(response, list, "商户资源数据");
    }

    /**
     * 获取商户资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchants:resource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merResourceService.selectMerResourceById(id));
    }

    /**
     * 新增商户资源
     */
    @PreAuthorize("@ss.hasPermi('merchants:resource:add')")
    @Log(title = "商户资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerResource merResource)
    {
        return toAjax(merResourceService.insertMerResource(merResource));
    }

    /**
     * 修改商户资源
     */
    @PreAuthorize("@ss.hasPermi('merchants:resource:edit')")
    @Log(title = "商户资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerResource merResource)
    {
        return toAjax(merResourceService.updateMerResource(merResource));
    }

    /**
     * 删除商户资源
     */
    @PreAuthorize("@ss.hasPermi('merchants:resource:remove')")
    @Log(title = "商户资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merResourceService.deleteMerResourceByIds(ids));
    }
}
