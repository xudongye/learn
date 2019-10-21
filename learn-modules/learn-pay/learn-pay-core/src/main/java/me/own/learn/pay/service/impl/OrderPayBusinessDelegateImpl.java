package me.own.learn.pay.service.impl;

import me.own.commons.base.utils.price.PriceUtils;
import me.own.learn.order.constant.OrderConstant;
import me.own.learn.order.service.OrderService;
import me.own.learn.order.vo.OrderVo;
import me.own.learn.pay.bo.PayBusinessBo;
import me.own.learn.pay.service.PayBusinessDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("OrderPayBusinessDelegateImpl")
public class OrderPayBusinessDelegateImpl implements PayBusinessDelegate {

    private static Logger LOGGER = LoggerFactory.getLogger(OrderPayBusinessDelegateImpl.class);

    @Autowired
    private OrderService orderService;

    @Override
    public boolean isAlreadyPaid(PayBusinessBo payBusinessBo) {
        for (String businessId : payBusinessBo.getBusinessIds()) {
            if (orderService.isOrderPaid(businessId))
                return true;
        }
        return false;
    }

    @Override
    public double getPaidPrice(PayBusinessBo payBusinessBo) {
        double total_fee = 0;
        for (String businessId : payBusinessBo.getBusinessIds()) {
            OrderVo order = orderService.getOrderById(businessId);
            total_fee += order.getTotalPrice() - order.getSubtractValue();
        }
        return PriceUtils.ConvertDecimalPoint(total_fee, 2);
    }

    @Override
    public String getSubject() {
        return "乐享支付";
    }

    @Override
    public String getBody() {
        return "订单";
    }

    @Override
    public void handlePayResult(PayBusinessBo payBusinessBo) {
        LOGGER.info("handler order pay result:{},{}", payBusinessBo.getBusinessIds(), payBusinessBo.getPayMethod());
        //mark order as paid
        OrderConstant.PaymentType paymentType = payBusinessBo.getPayMethod().equals("alipay") ? OrderConstant.PaymentType.Alipay
                : OrderConstant.PaymentType.Wxpay;
        for (String businessId : payBusinessBo.getBusinessIds()) {
            if (orderService.isOrderPaid(businessId)) {
                continue;
            }
            orderService.markOrderPaid(businessId, paymentType);
        }
        // The attach actually is a customer id
        // support for wx qr code pay
        if (payBusinessBo.getCustomerId() != 0L) {
            LOGGER.info("send notification order payment complete to customer: {}", payBusinessBo.getCustomerId());
            //TODO NOTIFICATION CUSTOMER
        }
    }
}
