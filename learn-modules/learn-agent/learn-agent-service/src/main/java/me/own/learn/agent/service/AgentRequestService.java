package me.own.learn.agent.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.agent.dto.AgentRequestDto;
import me.own.learn.agent.dto.AgentRequestHandleDto;
import me.own.learn.agent.vo.AgentRequestVo;

/**
 * @author yexudong
 * @date 2019/7/5 9:46
 */
public interface AgentRequestService {

    AgentRequestVo create(AgentRequestDto requestDto);

    AgentRequestVo update(AgentRequestDto requestDto);

    AgentRequestVo handle(AgentRequestHandleDto handleDto);

    PageQueryResult<AgentRequestVo> page(int pageNum, int pageSize, AgentRequestQueryCondition condition);
}
