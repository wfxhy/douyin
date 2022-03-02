package com.toker.project.task.controller;

import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.douyin.basis.service.interfaces.DyBasisService;
import com.toker.project.douyin.service.IDouyinUserService;
import com.toker.project.task.domain.TaskDyPublish;
import com.toker.project.task.service.IPublishTaskService;
import com.toker.project.task.service.ITaskDyPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 抖音推送记录Controller
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
@RestController
@RequestMapping("/task/taskDyPublish")
public class TaskDyPublishController extends BaseController {
    @Autowired
    private ITaskDyPublishService taskDyPublishService;
    @Autowired
    private IPublishTaskService publishTaskService;
    @Autowired
    private IDouyinUserService douyinUserService;
    @Autowired
    private DyBasisService dyBasisService;

    /**
     * 查询抖音推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskDyPublish taskDyPublish) {
        startPage();
        List<TaskDyPublish> list = taskDyPublishService.selectTaskDyPublishList(taskDyPublish);
        return getDataTable(list);
    }

    /**
     * 导出抖音推送记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:export')")
    @Log(title = "抖音推送记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TaskDyPublish taskDyPublish) {
        List<TaskDyPublish> list = taskDyPublishService.selectTaskDyPublishList(taskDyPublish);
        ExcelUtil<TaskDyPublish> util = new ExcelUtil<TaskDyPublish>(TaskDyPublish.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取抖音推送记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(taskDyPublishService.selectTaskDyPublishById(id));
    }

    /**
     * 新增抖音推送记录
     */
    @PreAuthorize("@ss.hasPermi('task:task:add')")
    @Log(title = "抖音推送记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskDyPublish taskDyPublish) {
        return toAjax(taskDyPublishService.insertTaskDyPublish(taskDyPublish));
    }

    /**
     * 修改抖音推送记录
     */
    @PreAuthorize("@ss.hasPermi('task:task:edit')")
    @Log(title = "抖音推送记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskDyPublish taskDyPublish) {
        return toAjax(taskDyPublishService.updateTaskDyPublish(taskDyPublish));
    }

    /**
     * 删除抖音推送记录
     */
    @PreAuthorize("@ss.hasPermi('task:task:remove')")
    @Log(title = "抖音推送记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(taskDyPublishService.deleteTaskDyPublishByIds(ids));
    }
}
