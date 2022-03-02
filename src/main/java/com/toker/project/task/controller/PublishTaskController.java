package com.toker.project.task.controller;

import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.task.domain.PublishTask;
import com.toker.project.task.service.IPublishTaskService;
import com.toker.project.task.service.ITaskDyPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待发布视频的任务Controller
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
@RestController
@RequestMapping("/task/task")
public class PublishTaskController extends BaseController {
    @Autowired
    private IPublishTaskService publishTaskService;
    @Autowired
    private ITaskDyPublishService taskDyPublishService;

    /**
     * 查询待发布视频的任务列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublishTask publishTask) {
        startPage();
        List<PublishTask> list = publishTaskService.selectPublishTaskList(publishTask);
        return getDataTable(list);
    }

    /**
     * 导出待发布视频的任务列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:export')")
    @Log(title = "待发布视频的任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PublishTask publishTask) {
        List<PublishTask> list = publishTaskService.selectPublishTaskList(publishTask);
        ExcelUtil<PublishTask> util = new ExcelUtil<PublishTask>(PublishTask.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取待发布视频的任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(publishTaskService.selectPublishTaskById(id));
    }

    /**
     * 新增待发布视频的任务
     */
    @PreAuthorize("@ss.hasPermi('task:task:add')")
    @Log(title = "待发布视频的任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublishTask publishTask) {
        return toAjax(publishTaskService.insertPublishTask(publishTask));
    }

    /**
     * 修改待发布视频的任务
     */
    @PreAuthorize("@ss.hasPermi('task:task:edit')")
    @Log(title = "待发布视频的任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublishTask publishTask) {
        return toAjax(publishTaskService.updatePublishTask(publishTask));
    }

    /**
     * 删除待发布视频的任务
     */
    @PreAuthorize("@ss.hasPermi('task:task:remove')")
    @Log(title = "待发布视频的任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(publishTaskService.deletePublishTaskByIds(ids));
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard(PublishTask publishTask) {
        Map<String, Object> result = new HashMap<>();

        //累计参与数
        long canyu = publishTaskService.getJoinCount(publishTask);
        result.put("canyu", canyu);
        //累计播放数
        long bofang = taskDyPublishService.getPlayCount(publishTask);
        result.put("bofang", bofang);
        //累计点赞数
        long dianzan = taskDyPublishService.getDiggCount(publishTask);
        result.put("dianzan", dianzan);
        //累计评论数
        long pinglun = taskDyPublishService.getCommentCount(publishTask);
        result.put("pinglun", pinglun);

        return result;
    }
}
