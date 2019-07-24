package me.own.learn.agent.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.agent.dao.AgentDao;
import me.own.learn.agent.po.Agent;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/7/24 14:37
 */
@Repository
public class AgentDaoImpl extends BaseDaoImpl<Agent> implements AgentDao {
    @Override
    protected Class<Agent> getEntityClass() {
        return Agent.class;
    }
}
