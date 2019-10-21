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


    /***
     * Get order vo by order id
     * @param orderid
     * @return
     */
    OrderVo getOrderById(String orderid);

    /***
     * Mark order status as paid status
     * @param orderid
     * @param paymentType alipay, wxpay, coupon
     */
    void markOrderPaid(String orderid, OrderConstant.PaymentType paymentType);

    /***
     * Check if order has already been paid
     * @param orderid order's id
     * @return true indicates paid, other unpaid
     */
    boolean isOrderPaid(String orderid);
}
