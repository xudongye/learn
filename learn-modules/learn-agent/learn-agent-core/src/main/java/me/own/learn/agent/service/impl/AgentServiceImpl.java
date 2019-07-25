package me.own.learn.agent.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.agent.dao.AgentDao;
import me.own.learn.agent.dto.AgentDto;
import me.own.learn.agent.exception.AgentNotFoundException;
import me.own.learn.agent.exception.AgentRepetitionException;
import me.own.learn.agent.po.Agent;
import me.own.learn.agent.service.AgentQueryCondition;
import me.own.learn.agent.service.AgentService;
import me.own.learn.agent.vo.AgentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static me.own.commons.base.utils.date.DateTimeUtils.ioFomat;
import static me.own.commons.base.utils.date.DateTimeUtils.plusOneDay;

/**
 * @author yexudong
 * @date 2019/7/24 14:57
 */
@Service
public class AgentServiceImpl implements AgentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentServiceImpl.class);

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
        LOGGER.info("create new agent {} by customer {} request generate admin {}.", agent.getId(), agent.getCustomerId(), agent.getAdminId());
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

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<AgentVo> page(int pageNum, int pageSize, AgentQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Agent.class);
        if (condition != null) {
            if (condition.getEmail() != null) {
                query.setSimpleCondition("email", condition.getEmail(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getAddress() != null) {
                query.setSimpleCondition("address", condition.getAddress(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getBusinessLicense() != null) {
                query.setSimpleCondition("businessLicense", condition.getBusinessLicense(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getTelephone() != null) {
                query.setSimpleCondition("telephone", condition.getTelephone(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getAdminId() != null) {
                query.setSimpleCondition("adminId", condition.getAdminId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getCustomerId() != null) {
                query.setSimpleCondition("customerId", condition.getCustomerId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getParentId() != null) {
                query.setSimpleCondition("parentId", condition.getParentId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getCreateFrom() != null && null == condition.getCreateTo()) {
                query.setSimpleCondition("createTime", ioFomat(condition.getCreateFrom()), QueryConstants.SimpleQueryMode.GreaterEqual);
            }
            if (condition.getCreateTo() != null && null == condition.getCreateFrom()) {
                query.setSimpleCondition("createTime", plusOneDay(ioFomat(condition.getCreateTo())), QueryConstants.SimpleQueryMode.LessEqual);
            }
            if (null != condition.getCreateFrom() && null != condition.getCreateTo()) {
                List<String> list = new ArrayList<String>();
                list.add(ioFomat(condition.getCreateFrom()));
                list.add(plusOneDay(ioFomat(condition.getCreateTo())));
                query.setComplexCondition("createTime", list, QueryConstants.ComplexQueryMode.Between, QueryConstants.QueryType.Conjunction);
            }
        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("createTime");
        order.setOder(QueryOrder.ASC);
        orders.add(order);

        PageQueryResult<Agent> pageQueryResult = agentDao.pageQuery(pageNum, pageSize, query, orders);
        return pageQueryResult.mapItems(AgentVo.class);
    }
}
