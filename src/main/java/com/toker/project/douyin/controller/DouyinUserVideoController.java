package com.toker.project.douyin.controller;

import com.toker.common.utils.ServletUtils;
import com.toker.common.utils.poi.ExcelUtil;
import com.toker.framework.aspectj.lang.annotation.Log;
import com.toker.framework.aspectj.lang.enums.BusinessType;
import com.toker.framework.security.LoginUser;
import com.toker.framework.security.service.TokenService;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.framework.web.page.TableDataInfo;
import com.toker.project.douyin.domain.DouyinUserVideo;
import com.toker.project.douyin.service.IDouyinUserVideoService;
import com.toker.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 抖音用户转发商户视频记录Controller
 *
 * @author huohuzhihui
 * @date 2021-03-26
 */
@RestController
@RequestMapping("/douyin/douyinUserVideo")
public class DouyinUserVideoController extends BaseController {
    @Autowired
    private IDouyinUserVideoService douyinUserVideoService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询抖音用户转发商户视频记录列表
     */
    @PreAuthorize("@ss.hasPermi('douyin:douyinUserVideo:list')")
    @GetMapping("/list")
    public TableDataInfo list(DouyinUserVideo douyinUserVideo) {
        //只查询当前商户的
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser sysUser = loginUser.getUser();

        douyinUserVideo.setMerchantId(sysUser.getDeptId());

        startPage();
        List<DouyinUserVideo> list = douyinUserVideoService.selectDouyinUserVideoList(douyinUserVideo);
        return getDataTable(list);
    }

    /**
     * 导出抖音用户转发商户视频记录列表
     */
    @PreAuthorize("@ss.hasPermi('douyin:douyinUserVideo:export')")
    @Log(title = "抖音用户转发商户视频记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DouyinUserVideo douyinUserVideo) {
        List<DouyinUserVideo> list = douyinUserVideoService.selectDouyinUserVideoList(douyinUserVideo);
        ExcelUtil<DouyinUserVideo> util = new ExcelUtil<DouyinUserVideo>(DouyinUserVideo.class);
        return util.exportExcel(list, "douyinUserVideo");
    }

    /**
     * 获取抖音用户转发商户视频记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('douyin:douyinUserVideo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(douyinUserVideoService.selectDouyinUserVideoById(id));
    }

    /**
     * 新增抖音用户转发商户视频记录
     */
    @PreAuthorize("@ss.hasPermi('douyin:douyinUserVideo:add')")
    @Log(title = "抖音用户转发商户视频记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DouyinUserVideo douyinUserVideo) {
        return toAjax(douyinUserVideoService.insertDouyinUserVideo(douyinUserVideo));
    }

    /**
     * 修改抖音用户转发商户视频记录
     */
    @PreAuthorize("@ss.hasPermi('douyin:douyinUserVideo:edit')")
    @Log(title = "抖音用户转发商户视频记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DouyinUserVideo douyinUserVideo) {
        return toAjax(douyinUserVideoService.updateDouyinUserVideo(douyinUserVideo));
    }

    /**
     * 删除抖音用户转发商户视频记录
     */
    @PreAuthorize("@ss.hasPermi('douyin:douyinUserVideo:remove')")
    @Log(title = "抖音用户转发商户视频记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(douyinUserVideoService.deleteDouyinUserVideoByIds(ids));
    }
}
