package com.toker.project.agent.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.toker.common.utils.SecurityUtils;
import com.toker.project.system.domain.SysDept;
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
import com.toker.project.agent.domain.Agent;
import com.toker.project.agent.service.IAgentService;
import com.toker.framework.web.controller.BaseController;
import com.toker.framework.web.domain.AjaxResult;
import com.toker.common.utils.poi.ExcelUtil;

/**
 * 代理商管理Controller
 * 
 * @author wf
 * @date 2022-02-28
 */
@RestController
@RequestMapping("/agent/agent")
public class AgentController extends BaseController
{
    @Autowired
    private IAgentService agentService;

    /**
     * 查询代理商管理列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:list')")
    @GetMapping("/list")
    public AjaxResult list(Agent agent)
    {
        agent.setId(SecurityUtils.getLoginUser().getUser().getAgentId());
        List<Agent> list = agentService.selectAgentList(agent);
        return AjaxResult.success(list);
    }

    /**
     * 导出代理商管理列表
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:export')")
    @Log(title = "代理商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Agent agent)
    {
        List<Agent> list = agentService.selectAgentList(agent);
        ExcelUtil<Agent> util = new ExcelUtil<Agent>(Agent.class);
        util.exportExcel(response, list, "代理商管理数据");
    }

    /**
     * 获取代理商管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(agentService.selectAgentById(id));
    }

    /**
     * 新增代理商管理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:add')")
    @Log(title = "代理商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Agent agent)
    {
        return toAjax(agentService.insertAgent(agent));
    }

    /**
     * 修改代理商管理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:edit')")
    @Log(title = "代理商管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Agent agent)
    {
        return toAjax(agentService.updateAgent(agent));
    }

    /**
     * 删除代理商管理
     */
    @PreAuthorize("@ss.hasPermi('agent:agent:remove')")
    @Log(title = "代理商管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(agentService.deleteAgentByIds(ids));
    }

    @GetMapping("/treeselect")
    public AjaxResult treeselect(Agent agent)
    {
        agent.setId(SecurityUtils.getLoginUser().getUser().getAgentId());
        List<Agent> agents = agentService.selectAgentList(agent);
        return AjaxResult.success(agentService.buildAgentTreeSelect(agents));
    }
}
