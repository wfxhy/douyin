package com.toker.project.agent.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.toker.common.utils.DateUtils;
import com.toker.common.utils.StringUtils;
import com.toker.framework.web.domain.TreeSelect;
import com.toker.project.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toker.project.agent.mapper.AgentMapper;
import com.toker.project.agent.domain.Agent;
import com.toker.project.agent.service.IAgentService;

/**
 * 代理商管理Service业务层处理
 * 
 * @author wf
 * @date 2022-02-28
 */
@Service
public class AgentServiceImpl implements IAgentService 
{
    @Autowired
    private AgentMapper agentMapper;

    /**
     * 查询代理商管理
     * 
     * @param id 代理商管理主键
     * @return 代理商管理
     */
    @Override
    public Agent selectAgentById(Long id)
    {
        return agentMapper.selectAgentById(id);
    }

    /**
     * 查询代理商管理列表
     * 
     * @param agent 代理商管理
     * @return 代理商管理
     */
    @Override
    public List<Agent> selectAgentList(Agent agent)
    {
        return agentMapper.selectAgentList(agent);
    }

    /**
     * 新增代理商管理
     * 
     * @param agent 代理商管理
     * @return 结果
     */
    @Override
    public int insertAgent(Agent agent)
    {
        agent.setCreateTime(DateUtils.getNowDate());
        return agentMapper.insertAgent(agent);
    }

    /**
     * 修改代理商管理
     * 
     * @param agent 代理商管理
     * @return 结果
     */
    @Override
    public int updateAgent(Agent agent)
    {
        agent.setUpdateTime(DateUtils.getNowDate());
        return agentMapper.updateAgent(agent);
    }

    /**
     * 批量删除代理商管理
     * 
     * @param ids 需要删除的代理商管理主键
     * @return 结果
     */
    @Override
    public int deleteAgentByIds(Long[] ids)
    {
        return agentMapper.deleteAgentByIds(ids);
    }

    /**
     * 删除代理商管理信息
     * 
     * @param id 代理商管理主键
     * @return 结果
     */
    @Override
    public int deleteAgentById(Long id)
    {
        return agentMapper.deleteAgentById(id);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param agents 代理商列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildAgentTreeSelect(List<Agent> agents){
        List<Agent> agentTrees = buildAgentTree(agents);
        return agentTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<Agent> buildAgentTree(List<Agent> agents)
    {
        List<Agent> returnList = new ArrayList<Agent>();
        List<Long> tempList = new ArrayList<Long>();
        for (Agent agent : agents)
        {
            tempList.add(agent.getId());
        }
        for (Agent agent : agents)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(agent.getParentId()))
            {
                recursionFn(agents, agent);
                returnList.add(agent);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = agents;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<Agent> agents, Agent agent)
    {
        // 得到子节点列表
        List<Agent> childList = getChildList(agents, agent);
        agent.setChildren(childList);
        for (Agent tChild : childList)
        {
            if (hasChild(agents, tChild))
            {
                recursionFn(agents, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<Agent> getChildList(List<Agent> agents, Agent agent)
    {
        List<Agent> tlist = new ArrayList<Agent>();
        Iterator<Agent> it = agents.iterator();
        while (it.hasNext())
        {
            Agent n = (Agent) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == agent.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Agent> agents, Agent agent)
    {
        return getChildList(agents, agent).size() > 0 ? true : false;
    }
}
