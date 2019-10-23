package me.own.learn.agent.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.date.DateTimeUtils;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.admin.dto.AdminDto;
import me.own.learn.admin.service.AdminService;
import me.own.learn.admin.vo.AdminVo;
import me.own.learn.agent.constant.AgentConstant;
import me.own.learn.agent.dao.AgentRequestDao;
import me.own.learn.agent.dto.AgentDto;
import me.own.learn.agent.dto.AgentRequestDto;
import me.own.learn.agent.dto.AgentRequestHandleDto;
import me.own.learn.agent.exception.AgentRequestNotFoundException;
import me.own.learn.agent.po.AgentRequest;
import me.own.learn.agent.service.AgentRequestQueryCondition;
import me.own.learn.agent.service.AgentRequestService;
import me.own.learn.agent.service.AgentService;
import me.own.learn.agent.vo.AgentRequestVo;
import me.own.learn.agent.vo.AgentVo;
import me.own.learn.configuration.delegate.LearnConfigurationServiceDelegate;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import me.own.learn.event.service.EventService;
import me.own.learn.event.service.message.agent.AgentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/7/5 10:00
 */
@Service
public class AgentRequestServiceImpl implements AgentRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentRequestServiceImpl.class);

    @Autowired
    private AgentRequestDao agentRequestDao;

    @Autowired
    private EventService eventService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AdminService adminService;

    private LearnConfigurationServiceDelegate delegate = LearnConfigurationServiceDelegate.getInstance();

    private static final Long AGENT_ROLE = 2L;

    @Override
    @Transactional
    public AgentRequestVo create(AgentRequestDto requestDto) {
        AgentRequest agentRequest = Mapper.Default().map(requestDto, AgentRequest.class);
        agentRequest.setCreateTime(new Date());
        agentRequest.setAgentType(requestDto.getAgentType().getCode());
        agentRequest.setStatus(requestDto.getRequestStatus().getCode());
        agentRequestDao.create(agentRequest);
        LOGGER.info("create new agent request {} from customer {}", agentRequest.getId(), agentRequest.getCustomerId());
        return Mapper.Default().map(agentRequest, AgentRequestVo.class);
    }

    @Override
    @Transactional
    public AgentRequestVo update(AgentRequestDto requestDto) {
        //TODO
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public AgentRequestVo getById(long requestId) {
        AgentRequest agentRequest = agentRequestDao.get(requestId);
        if (agentRequest == null) {
            throw new AgentRequestNotFoundException();
        }
        return Mapper.Default().map(agentRequest, AgentRequestVo.class);
    }

    @Override
    @Transactional
    public AgentRequestVo handle(AgentRequestHandleDto handleDto) {
        AgentRequest agentRequest = agentRequestDao.get(handleDto.getId());

        if (agentRequest == null) {
            throw new AgentRequestNotFoundException();
        }
        CustomerVo customerVo = customerService.getById(agentRequest.getCustomerId());
        agentRequest.setStatus(handleDto.getRequestStatus().getCode());
        agentRequest.setRemark(handleDto.getRemark());
        agentRequest.setHandlerId(handleDto.getHandlerId());
        agentRequest.setHandledTime(new Date());
        LOGGER.info("agent {} request been {} with remark {}", agentRequest.getName(), handleDto.getRequestStatus().getName(), handleDto.getRemark());
        agentRequestDao.update(agentRequest);
        //判断受理状态是否为批准成为分销商
        if (handleDto.getRequestStatus().getCode() == AgentConstant.AgentRequestStatus.approval.getCode()) {

            AdminDto adminDto = new AdminDto();
            adminDto.setCellphone(agentRequest.getTelephone());
            adminDto.setEmail(agentRequest.getEmail());
            adminDto.setName(agentRequest.getName());
            adminDto.setHeadImg(customerVo.getHeadImage());
            adminDto.setRoleId(AGENT_ROLE);
            adminDto.setPassword(delegate.getConfiguration().getDefaultPassword());
            AdminVo adminVo = adminService.create(adminDto);
            LOGGER.info("create admin {} from agent request pass.", adminVo.getName());

            AgentDto agentDto = new AgentDto();
            //分销商类型
            agentDto.setAgentType(agentRequest.getAgentType());
            //父级代理商
            //FIXME
//            agentDto.setParentId(customerVo.getSourceAgentId());
            //新增管理员
            agentDto.setAdminId(adminVo.getId());
            agentDto.setAssignedQR(handleDto.getAssignedQR());
            //申请会员
            agentDto.setCustomerId(agentRequest.getCustomerId());
            agentDto.setName(agentRequest.getName());
            agentDto.setTelephone(agentRequest.getTelephone());
            agentDto.setAddress(agentRequest.getAddress());
            agentDto.setEmail(agentRequest.getEmail());
            //是否为顶级分销商
            //FIXME
//            agentDto.setRoot(customerVo.getSourceAgentId() == 1);
            //个人的可以发展下线
            agentDto.setMemberJoinShareEnable(handleDto.getMemberJoinShareEnable());
            agentDto.setChildrenRate(handleDto.getChildrenRate());
            AgentVo agentVo = agentService.create(agentDto);
            LOGGER.info("create new agent {} success", agentVo.getName());

            eventService.enqueue(EventService.EventName.AgentEvent.AGENT_PASS,
                    new AgentMessage(agentRequest.getName(), customerVo.getHeadImage(), agentRequest.getTelephone(), agentRequest.getEmail()));
        }

        return Mapper.Default().map(agentRequest, AgentRequestVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<AgentRequestVo> page(int pageNum, int pageSize, AgentRequestQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(AgentRequest.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (condition != null) {
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getAddress() != null) {
                query.setSimpleCondition("address", condition.getAddress(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getEmail() != null) {
                query.setSimpleCondition("email", condition.getEmail(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getTelephone() != null) {
                query.setSimpleCondition("telephone", condition.getTelephone(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (null != condition.getCreateTimeFrom() && null == condition.getCreateTimeTo()) {
                query.setSimpleCondition("createTime", sdf.format(new Date(condition.getCreateTimeFrom())), QueryConstants.SimpleQueryMode.GreaterEqual);
            }
            if (null != condition.getCreateTimeTo() && null == condition.getCreateTimeFrom()) {
                query.setSimpleCondition("createTime", sdf.format(DateTimeUtils.plusOneDay(new Date(condition.getCreateTimeTo()))), QueryConstants.SimpleQueryMode.LessEqual);
            }
            if (null != condition.getCreateTimeFrom() && null != condition.getCreateTimeTo()) {
                List<String> list = new ArrayList<String>();
                list.add(sdf.format(condition.getCreateTimeFrom()));
                list.add(sdf.format(DateTimeUtils.plusOneDay(new Date(condition.getCreateTimeTo()))));
                query.setComplexCondition("createTime", list, QueryConstants.ComplexQueryMode.Between, QueryConstants.QueryType.Conjunction);
            }
            if (null != condition.getHandledTimeFrom() && null == condition.getHandledTimeTo()) {
                query.setSimpleCondition("handledTime", sdf.format(new Date(condition.getHandledTimeFrom())), QueryConstants.SimpleQueryMode.GreaterEqual);
            }
            if (null != condition.getHandledTimeTo() && null == condition.getHandledTimeFrom()) {
                query.setSimpleCondition("handledTime", sdf.format(DateTimeUtils.plusOneDay(new Date(condition.getHandledTimeTo()))), QueryConstants.SimpleQueryMode.LessEqual);
            }
            if (null != condition.getHandledTimeFrom() && null != condition.getHandledTimeTo()) {
                List<String> list = new ArrayList<String>();
                list.add(sdf.format(condition.getHandledTimeFrom()));
                list.add(sdf.format(DateTimeUtils.plusOneDay(new Date(condition.getHandledTimeTo()))));
                query.setComplexCondition("handledTime", list, QueryConstants.ComplexQueryMode.Between, QueryConstants.QueryType.Conjunction);
            }

        }

        List<QueryOrder> queryOrders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("id");
        order.setOder(QueryOrder.DESC);
        queryOrders.add(order);

        PageQueryResult<AgentRequest> result = agentRequestDao.pageQuery(pageNum, pageSize, query, queryOrders);

        return result.mapItems(AgentRequestVo.class);
    }
}
