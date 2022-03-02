package com.toker.project.video.controller;

import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.video.domain.FfmpegBgm;
import com.toker.project.video.service.IFfmpegBgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 视频背景音乐Controller
 *
 * @author huohuzhihui
 * @date 2021-06-20
 */
@RestController
@RequestMapping("/video/bgm")
public class FfmpegBgmController extends BaseController {
    @Autowired
    private IFfmpegBgmService ffmpegBgmService;

    /**
     * 查询视频背景音乐列表
     */
    @PreAuthorize("@ss.hasPermi('video:bgm:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfmpegBgm ffmpegBgm) {
        startPage();
        List<FfmpegBgm> list = ffmpegBgmService.selectFfmpegBgmList(ffmpegBgm);
        return getDataTable(list);
    }

    /**
     * 导出视频背景音乐列表
     */
    @PreAuthorize("@ss.hasPermi('video:bgm:export')")
    @Log(title = "视频背景音乐", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfmpegBgm ffmpegBgm) {
        List<FfmpegBgm> list = ffmpegBgmService.selectFfmpegBgmList(ffmpegBgm);
        ExcelUtil<FfmpegBgm> util = new ExcelUtil<FfmpegBgm>(FfmpegBgm.class);
        return util.exportExcel(list, "bgm");
    }

    /**
     * 获取视频背景音乐详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:bgm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(ffmpegBgmService.selectFfmpegBgmById(id));
    }

    /**
     * 新增视频背景音乐
     */
    @PreAuthorize("@ss.hasPermi('video:bgm:add')")
    @Log(title = "视频背景音乐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfmpegBgm ffmpegBgm) {
        return toAjax(ffmpegBgmService.insertFfmpegBgm(ffmpegBgm));
    }

    /**
     * 修改视频背景音乐
     */
    @PreAuthorize("@ss.hasPermi('video:bgm:edit')")
    @Log(title = "视频背景音乐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfmpegBgm ffmpegBgm) {
        return toAjax(ffmpegBgmService.updateFfmpegBgm(ffmpegBgm));
    }

    /**
     * 删除视频背景音乐
     */
    @PreAuthorize("@ss.hasPermi('video:bgm:remove')")
    @Log(title = "视频背景音乐", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(ffmpegBgmService.deleteFfmpegBgmByIds(ids));
    }
}
