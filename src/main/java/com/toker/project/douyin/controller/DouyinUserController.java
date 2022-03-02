package com.toker.project.douyin.controller;

import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.douyin.domain.DouyinUser;
import com.toker.project.douyin.service.IDouyinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 抖音用户Controller
 *
 * @author huohuzhihui
 * @date 2021-03-25
 */
@RestController
@RequestMapping("/douyin/user")
public class DouyinUserController extends BaseController {
    @Autowired
    private IDouyinUserService douyinUserService;

    /**
     * 查询抖音用户列表
     */
    @PreAuthorize("@ss.hasPermi('douyin:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(DouyinUser douyinUser) {
        startPage();
        List<DouyinUser> list = douyinUserService.selectDouyinUserList(douyinUser);
        return getDataTable(list);
    }

    /**
     * 导出抖音用户列表
     */
    @PreAuthorize("@ss.hasPermi('douyin:user:export')")
    @Log(title = "抖音用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DouyinUser douyinUser) {
        List<DouyinUser> list = douyinUserService.selectDouyinUserList(douyinUser);
        ExcelUtil<DouyinUser> util = new ExcelUtil<DouyinUser>(DouyinUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取抖音用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('douyin:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(douyinUserService.selectDouyinUserById(id));
    }

    /**
     * 新增抖音用户
     */
    @PreAuthorize("@ss.hasPermi('douyin:user:add')")
    @Log(title = "抖音用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DouyinUser douyinUser) {
        return toAjax(douyinUserService.insertDouyinUser(douyinUser));
    }

    /**
     * 修改抖音用户
     */
    @PreAuthorize("@ss.hasPermi('douyin:user:edit')")
    @Log(title = "抖音用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DouyinUser douyinUser) {
        return toAjax(douyinUserService.updateDouyinUser(douyinUser));
    }

    /**
     * 删除抖音用户
     */
    @PreAuthorize("@ss.hasPermi('douyin:user:remove')")
    @Log(title = "抖音用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(douyinUserService.deleteDouyinUserByIds(ids));
    }
}
