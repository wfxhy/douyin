package com.toker.project.task.controller;

import com.toker.common.constant.Constants;
import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.config.RuoYiConfig;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.merchants.domain.MerResource;
import com.toker.project.merchants.service.IMerResourceService;
import com.toker.project.task.domain.TaskVideoMerge;
import com.toker.project.task.service.ITaskVideoMergeService;
import com.toker.project.video.config.FFmpegConfig;
import com.toker.project.video.utils.VideoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频合成记录Controller
 *
 * @author huohuzhihui
 * @date 2021-06-28
 */
@RestController
@RequestMapping("/task/taskVideoMerge")
public class TaskVideoMergeController extends BaseController {
    @Autowired
    private ITaskVideoMergeService taskVideoMergeService;

    /**
     * 查询视频合成记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskVideoMerge taskVideoMerge) {
        startPage();
        List<TaskVideoMerge> list = taskVideoMergeService.selectTaskVideoMergeList(taskVideoMerge);
        return getDataTable(list);
    }

    /**
     * 导出视频合成记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:export')")
    @Log(title = "视频合成记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TaskVideoMerge taskVideoMerge) {
        List<TaskVideoMerge> list = taskVideoMergeService.selectTaskVideoMergeList(taskVideoMerge);
        ExcelUtil<TaskVideoMerge> util = new ExcelUtil<TaskVideoMerge>(TaskVideoMerge.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取视频合成记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(taskVideoMergeService.selectTaskVideoMergeById(id));
    }

    /**
     * 新增视频合成记录
     */
    @PreAuthorize("@ss.hasPermi('task:task:add')")
    @Log(title = "视频合成记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskVideoMerge taskVideoMerge) {
        return toAjax(taskVideoMergeService.insertTaskVideoMerge(taskVideoMerge));
    }

    /**
     * 修改视频合成记录
     */
    @PreAuthorize("@ss.hasPermi('task:task:edit')")
    @Log(title = "视频合成记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskVideoMerge taskVideoMerge) {
        return toAjax(taskVideoMergeService.updateTaskVideoMerge(taskVideoMerge));
    }

    /**
     * 删除视频合成记录
     */
    @PreAuthorize("@ss.hasPermi('task:task:remove')")
    @Log(title = "视频合成记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(taskVideoMergeService.deleteTaskVideoMergeByIds(ids));
    }

    @Autowired
    private IMerResourceService merResourceService;



    @GetMapping("/get/{id}")
    public AjaxResult get(@PathVariable Long id){
        String res = this.taskVideoMergeService.getMergeResourceIds(id);
        List<String> videoPaths = new ArrayList<String>();
        // 本地资源路径
        String localPath = RuoYiConfig.getProfile();
        for (String resourceId : res.split(",")) {
            MerResource merResource = this.merResourceService.selectMerResourceById(Long.parseLong(resourceId));
            videoPaths.add((localPath + StringUtils.substringAfter(merResource.getResourceUrl(), Constants.RESOURCE_PREFIX)).replaceAll("/", "\\\\"));
        }
        MerResource audioResource = merResourceService.getRandomAudio(1L);
        String audioUrl = audioResource!=null?audioResource.getResourceUrl():"";
        String audioPath = null;
        if (StringUtils.isNotEmpty(audioUrl)) {
            audioPath = localPath + StringUtils.substringAfter(audioUrl, Constants.RESOURCE_PREFIX).replaceAll("/", "\\\\");
        }
        //生成合并完的视频路径
        String mergePath = FFmpegConfig.getTempAbsolutePath() + VideoUtil.nowTime() + ".mp4";
        return AjaxResult.success(VideoUtil.mergeVideo(mergePath, videoPaths, true, audioPath));
    }
}
