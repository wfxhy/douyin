package com.toker.project.agent.service;

import java.util.List;

import com.toker.framework.web.domain.TreeSelect;
import com.toker.project.agent.domain.Agent;
import com.toker.project.system.domain.SysDept;

/**
 * 代理商管理Service接口
 * 
 * @author wf
 * @date 2022-02-28
 */
public interface IAgentService 
{
    /**
     * 查询代理商管理
     * 
     * @param id 代理商管理主键
     * @return 代理商管理
     */
    public Agent selectAgentById(Long id);

    /**
     * 查询代理商管理列表
     * 
     * @param agent 代理商管理
     * @return 代理商管理集合
     */
    public List<Agent> selectAgentList(Agent agent);

    /**
     * 新增代理商管理
     * 
     * @param agent 代理商管理
     * @return 结果
     */
    public int insertAgent(Agent agent);

    /**
     * 修改代理商管理
     * 
     * @param agent 代理商管理
     * @return 结果
     */
    public int updateAgent(Agent agent);

    /**
     * 批量删除代理商管理
     * 
     * @param ids 需要删除的代理商管理主键集合
     * @return 结果
     */
    public int deleteAgentByIds(Long[] ids);

    /**
     * 删除代理商管理信息
     * 
     * @param id 代理商管理主键
     * @return 结果
     */
    public int deleteAgentById(Long id);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param agents 代理商列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildAgentTreeSelect(List<Agent> agents);

    /**
     * 构建前端所需要树结构
     *
     * @param agents 部门列表
     * @return 树结构列表
     */
    public List<Agent> buildAgentTree(List<Agent> agents);
}
