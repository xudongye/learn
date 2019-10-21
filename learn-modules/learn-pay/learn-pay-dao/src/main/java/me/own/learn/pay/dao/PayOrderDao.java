package me.own.learn.pay.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.pay.po.PayOrder;

public interface PayOrderDao extends BaseDao<PayOrder> {

    /***
     * 根据第三方交易编号查询支付信息
     * @param outTradeNo
     * @return
     */
    PayOrder getByOutTradeNo(String outTradeNo);


    /**
     * 查询同时支付orderIds 所有订单的未支付 PayOrder
     *
     * @param businessIds  内部订单号数组
     * @param payMethod    支付方式 "alipay" "wxpay"
     * @param businessType 业务类型
     * @param tradeType    支付类型 "NATIVE" "JSAPI" for wxpay
     * @return 如果不存在则返回null
     */
    PayOrder queryNotPaidByBusinessIds(String[] businessIds, PayOrder.PayMethod payMethod, int businessType, String tradeType);

}
