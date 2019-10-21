package me.own.learn.order.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.commons.base.utils.price.PriceUtils;
import me.own.learn.order.constant.OrderConstant;
import me.own.learn.order.dao.OrderDao;
import me.own.learn.order.dao.OrderDetailDao;
import me.own.learn.order.dto.OrderDetailDto;
import me.own.learn.order.dto.OrderDto;
import me.own.learn.order.exception.OrderDetailNotNullException;
import me.own.learn.order.exception.OrderNotFoundException;
import me.own.learn.order.exception.UnfilledAddressException;
import me.own.learn.order.po.Order;
import me.own.learn.order.po.OrderDetail;
import me.own.learn.order.service.OrderQueryCondition;
import me.own.learn.order.service.OrderService;
import me.own.learn.order.vo.OrderDetailVo;
import me.own.learn.order.vo.OrderVo;
import org.apache.commons.collections.CollectionUtils;
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
    private OrderDetailDao detailDao;

    @Override
    @Transactional
    public OrderVo create(OrderDto orderDto) {
        //未选择地址
        if (orderDto.getAddressNo() == null) {
            throw new UnfilledAddressException();
        }
        Order order = Mapper.Default().map(orderDto, Order.class);
        order.setCreateTime(new Date());
        order.setDeleted(false);
        //初始化支付状态，待支付
        order.setStatus(OrderConstant.OrderStatus.unpaid.getCode());
        orderDao.create(order);
        LOGGER.info("create new order {} by customer {}", order.getOrderNo(), order.getCustomerId());
        //详情数据录入
        createDetail(order, orderDto.getDetails());
        return Mapper.Default().map(order, OrderVo.class);
    }

    //录入详情并计算总价
    private void createDetail(Order order, List<OrderDetailDto> detailDtos) {
        double totalPrice = 0.0;
        if (CollectionUtils.isNotEmpty(detailDtos)) {
            List<OrderDetail> details = Mapper.Default().mapArray(detailDtos, OrderDetail.class);
            for (OrderDetail detail : details) {
                detail.setOrderNo(order.getOrderNo());
                detailDao.create(detail);
                totalPrice += detail.getPrice() * detail.getCount();
            }
            totalPrice = PriceUtils.ConvertDecimalPoint(totalPrice, 2);
            //更新总价至订单
            order.setTotalPrice(totalPrice);
            //如果订单价格为0，订单价格更新为已支付
            if (totalPrice == 0.0) {
                order.setStatus(OrderConstant.OrderStatus.paid.getCode());
                LOGGER.info("order {} total price be {} change status {}",
                        order.getOrderNo(), totalPrice, OrderConstant.OrderStatus.paid.getName());
            }
            orderDao.update(order);
            LOGGER.info("order {} detail be read-in total price {}", order.getOrderNo(), totalPrice);
        } else {
            throw new OrderDetailNotNullException();
        }
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
        //更新为已发货
        order.setStatus(OrderConstant.OrderStatus.sentOut.getCode());
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

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetailVo> listByOrderNo(String orderNo) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(OrderDetail.class);
        query.setSimpleCondition("orderNo", orderNo, QueryConstants.SimpleQueryMode.Equal);
        List<OrderDetail> details =
                detailDao.filter(query, null, new QueryOrder("id", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(details)) {
            return Mapper.Default().mapArray(details, OrderDetailVo.class);
        }
        return null;
    }

    @Override
    public OrderVo getOrderById(String orderid) {
        return null;
    }

    @Override
    public void markOrderPaid(String orderid, OrderConstant.PaymentType paymentType) {

    }

    @Override
    public boolean isOrderPaid(String orderid) {
        return false;
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
