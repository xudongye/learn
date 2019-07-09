package me.own.learn.agent.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.agent.dao.AgentRequestDao;
import me.own.learn.agent.po.AgentRequest;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/7/5 9:44
 */
@Repository
public class AgentRequestDaoImpl extends BaseDaoImpl<AgentRequest> implements AgentRequestDao {
    @Override
    protected Class<AgentRequest> getEntityClass() {
        return AgentRequest.class;
    }
}
