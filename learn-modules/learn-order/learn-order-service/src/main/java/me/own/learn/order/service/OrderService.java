package me.own.learn.order.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.order.constant.OrderConstant;
import me.own.learn.order.dto.OrderDto;
import me.own.learn.order.vo.OrderDetailVo;
import me.own.learn.order.vo.OrderVo;

import java.util.List;


public interface OrderService {
    //新建订单
    OrderVo create(OrderDto orderDto);

    //录入订单快递信息
    OrderVo logisticsEnter(String orderNo, String logisticsCom, String logisticsNo);

    PageQueryResult<OrderVo> page(int pageNum, int pageSize, OrderQueryCondition condition);

    OrderVo updateStatusByOrderNo(String orderNo, OrderConstant.OrderStatus status);

    List<OrderDetailVo> listByOrderNo(String orderNo);
}
