package me.own.learn.agent.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.agent.dto.AgentDto;
import me.own.learn.agent.vo.AgentVo;

/**
 * @author yexudong
 * @date 2019/7/24 14:07
 */
public interface AgentService {

    AgentVo create(AgentDto agentDto);

    AgentVo getById(long agentId);

    PageQueryResult<AgentVo> page(int pageNum, int pageSize, AgentQueryCondition condition);

    AgentVo generateQrCodeById(long agentId);
}
