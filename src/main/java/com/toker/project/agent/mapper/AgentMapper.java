package com.toker.project.agent.mapper;

import java.util.List;
import com.toker.project.agent.domain.Agent;

/**
 * 代理商管理Mapper接口
 * 
 * @author wf
 * @date 2022-02-28
 */
public interface AgentMapper 
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
     * 删除代理商管理
     * 
     * @param id 代理商管理主键
     * @return 结果
     */
    public int deleteAgentById(Long id);

    /**
     * 批量删除代理商管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAgentByIds(Long[] ids);
}
