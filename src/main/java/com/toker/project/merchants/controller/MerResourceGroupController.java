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
import com.toker.project.merchants.domain.MerResourceGroup;
import com.toker.project.merchants.service.IMerResourceGroupService;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.web.page.TableDataInfo;

/**
 * 商户资源分组Controller
 * 
 * @author wf
 * @date 2022-02-28
 */
@RestController
@RequestMapping("/merchants/group")
public class MerResourceGroupController extends BaseController
{
    @Autowired
    private IMerResourceGroupService merResourceGroupService;

    @GetMapping("/getList")
    public AjaxResult getList(MerResourceGroup merResourceGroup)
    {
        List<MerResourceGroup> list = merResourceGroupService.selectMerResourceGroupList(merResourceGroup);
        return AjaxResult.success(list);
    }

    /**
     * 查询商户资源分组列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerResourceGroup merResourceGroup)
    {
        startPage();
        List<MerResourceGroup> list = merResourceGroupService.selectMerResourceGroupList(merResourceGroup);
        return getDataTable(list);
    }

    /**
     * 导出商户资源分组列表
     */
    @PreAuthorize("@ss.hasPermi('merchants:group:export')")
    @Log(title = "商户资源分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MerResourceGroup merResourceGroup)
    {
        List<MerResourceGroup> list = merResourceGroupService.selectMerResourceGroupList(merResourceGroup);
        ExcelUtil<MerResourceGroup> util = new ExcelUtil<MerResourceGroup>(MerResourceGroup.class);
        util.exportExcel(response, list, "商户资源分组数据");
    }

    /**
     * 获取商户资源分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchants:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(merResourceGroupService.selectMerResourceGroupById(id));
    }

    /**
     * 新增商户资源分组
     */
    @PreAuthorize("@ss.hasPermi('merchants:group:add')")
    @Log(title = "商户资源分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerResourceGroup merResourceGroup)
    {
        return toAjax(merResourceGroupService.insertMerResourceGroup(merResourceGroup));
    }

    /**
     * 修改商户资源分组
     */
    @PreAuthorize("@ss.hasPermi('merchants:group:edit')")
    @Log(title = "商户资源分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerResourceGroup merResourceGroup)
    {
        return toAjax(merResourceGroupService.updateMerResourceGroup(merResourceGroup));
    }

    /**
     * 删除商户资源分组
     */
    @PreAuthorize("@ss.hasPermi('merchants:group:remove')")
    @Log(title = "商户资源分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merResourceGroupService.deleteMerResourceGroupByIds(ids));
    }
}
