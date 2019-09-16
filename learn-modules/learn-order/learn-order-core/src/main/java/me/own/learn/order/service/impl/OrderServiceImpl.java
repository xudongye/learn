package me.own.learn.order.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.order.constant.OrderConstant;
import me.own.learn.order.dao.OrderDao;
import me.own.learn.order.dto.OrderDto;
import me.own.learn.order.exception.OrderNotFoundException;
import me.own.learn.order.po.Order;
import me.own.learn.order.service.OrderAddressService;
import me.own.learn.order.service.OrderQueryCondition;
import me.own.learn.order.service.OrderService;
import me.own.learn.order.vo.OrderAddressVo;
import me.own.learn.order.vo.OrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderAddressService addressService;

    @Override
    @Transactional
    public OrderVo create(OrderDto orderDto) {
        Long addressNo = orderDto.getAddress().getAddressNo();
        //是否引用旧地址，否则新建地址记录
        if (addressNo == null) {
            OrderAddressVo addressVo = addressService.create(orderDto.getAddress());
            addressNo = addressVo.getAddressNo();
        }
        Order order = Mapper.Default().map(orderDto, Order.class);
        order.setAddressNo(addressNo);
        order.setCreateTime(new Date());
        order.setDeleted(false);
        //待支付
        order.setStatus(OrderConstant.OrderStatus.unpaid.getCode());
        orderDao.create(order);
        LOGGER.info("create new order {} by customer {}", order.getOrderNo(), order.getCustomerId());
        return Mapper.Default().map(order, OrderVo.class);
    }

    @Override
    @Transactional
    public OrderVo logisticsEnter(String orderNo, String logisticsCom, String logisticsNo) {
        Order order = orderDao.get(orderNo);
        if (order == null || order.getDeleted()) {
            throw new OrderNotFoundException();
        }
        order.setLogisticsCom(logisticsCom);
        order.setLogisticsNo(logisticsNo);
        order.setLogisticsDate(new Date());
        orderDao.update(order);
        LOGGER.info("order {} enter logistics com {} no {}", orderNo, logisticsCom, logisticsNo);
        return Mapper.Default().map(order, OrderVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<OrderVo> page(int pageNum, int pageSize, OrderQueryCondition condition) {
        QueryCriteriaUtil query = condition != null ? criteriaUtil(condition) : new QueryCriteriaUtil(Order.class);
        query.setDeletedFalseCondition();
        List<QueryOrder> queryOrders = new ArrayList<>();
        QueryOrder queryOrder = new QueryOrder();
        queryOrder.setColumnName("createTime");
        queryOrder.setOder(QueryOrder.DESC);
        queryOrders.add(queryOrder);
        PageQueryResult<Order> pageQueryResult = orderDao.pageQuery(pageNum, pageSize, query, queryOrders);
        return pageQueryResult.mapItems(OrderVo.class);
    }

    @Override
    @Transactional
    public OrderVo updateStatusByOrderNo(String orderNo, OrderConstant.OrderStatus status) {
        Order order = orderDao.get(orderNo);
        if (order == null || order.getDeleted()) {
            throw new OrderNotFoundException();
        }
        order.setStatus(status.getCode());
        order.setModifyTime(new Date());
        orderDao.update(order);
        LOGGER.info("order {} status {} be update", orderNo, status.getName());
        return Mapper.Default().map(order, OrderVo.class);
    }


    private QueryCriteriaUtil criteriaUtil(OrderQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Order.class);
        if (condition.getCustomerId() != null) {
            query.setSimpleCondition("customerId", condition.getCustomerId() + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (condition.getStatus() != null) {
            query.setSimpleCondition("status", condition.getStatus() + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (condition.getLogisticsCom() != null) {
            query.setSimpleCondition("logisticsCom", condition.getLogisticsCom(), QueryConstants.SimpleQueryMode.Equal);
        }
        if (condition.getLogisticsNo() != null) {
            query.setSimpleCondition("logisticsNo", condition.getLogisticsNo(), QueryConstants.SimpleQueryMode.Equal);
        }
        return query;
    }
}
