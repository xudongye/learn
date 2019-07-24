package me.own.learn.agent.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.agent.dao.AgentDao;
import me.own.learn.agent.dto.AgentDto;
import me.own.learn.agent.exception.AgentNotFoundException;
import me.own.learn.agent.exception.AgentRepetitionException;
import me.own.learn.agent.po.Agent;
import me.own.learn.agent.service.AgentService;
import me.own.learn.agent.vo.AgentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yexudong
 * @date 2019/7/24 14:57
 */
@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentDao agentDao;

    @Override
    @Transactional
    public AgentVo create(AgentDto agentDto) {
        if (agentExist(agentDto.getCustomerId())) {
            throw new AgentRepetitionException();
        }
        Agent parentAgent = agentDao.get(agentDto.getParentId());
        //如果分销商被禁
        if (parentAgent == null || !parentAgent.getEnable()) {
            parentAgent = agentDao.get(1L);
        }
        parentAgent.useQR(agentDto.getAssignedQR());
        Agent agent = Mapper.Default().map(agentDto, Agent.class);
        agent.setAncestorChain(parentAgent.getAncestorChain() + parentAgent.getId() + "/");
        agent.setLayer(parentAgent.getLayer() + 1);
        agent.setRate(parentAgent.getChildrenRate());
        agent.setCreateTime(new Date());
        agent.setEnable(true);
        agentDao.create(agent);
        return Mapper.Default().map(agent, AgentVo.class);
    }

    private boolean agentExist(long customerId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Agent.class);
        query.setSimpleCondition("customerId", customerId + "", QueryConstants.SimpleQueryMode.Equal);
        return agentDao.getCount(query) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public AgentVo getById(long agentId) {
        Agent agent = agentDao.get(agentId);
        if (agent == null || !agent.getEnable()) {
            throw new AgentNotFoundException();
        }
        return Mapper.Default().map(agent, AgentVo.class);
    }
}
