package me.own.learn.pay.dao.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.pay.dao.PayOrderDao;
import me.own.learn.pay.po.PayOrder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class PayOrderImpl extends BaseDaoImpl<PayOrder> implements PayOrderDao {
    @Override
    protected Class<PayOrder> getEntityClass() {
        return PayOrder.class;
    }


    /**
     * sql example: SELECT p.id FROM PayOrder p
     *      JOIN PayToInnerBusiness ptb1 ON ( p.id = ptb1.PAY_BUSINESS_ID AND ptb1.businessType = 1 AND ptb1.businessId = '201608050005' )
     *      JOIN PayToInnerBusiness ptb2 ON ( p.id = ptb2.PAY_BUSINESS_ID AND ptb2.businessType = 1 AND ptb2.businessId = '201708090004' )
     *          WHERE p.isPaid = 0 and  p.payMethod = 'alipay';
     * @param businessIds 内部业务编号数组
     * @param payMethod 支付方式
     * @param businessType 业务类型
     * @param tradeType 支付类型 (NATIVE, JSAPI, APP)
     * @return
     */
    @Override
    public PayOrder queryNotPaidByBusinessIds(String[] businessIds, PayOrder.PayMethod payMethod, int businessType, String tradeType) {
        StringBuilder sql = new StringBuilder("SELECT p.id FROM PayOrder p ");
        for (int i = 0; i < businessIds.length; i ++) {
            String businessId = businessIds[i];
            sql.append(" JOIN PayToInnerBusiness ptb" + i
                    + " ON ( p.id = ptb" + i
                    + ".PAY_BUSINESS_ID AND ptb" + i
                    + ".businessType = " + businessType + " AND ptb" + i
                    + ".businessId = '" + businessId + "' ) ");
        }
        sql.append(" WHERE p.isPaid = 0  and p.payMethod = '" + payMethod + "'");
        if(StringUtils.isNotEmpty(tradeType)){
            sql.append(" and p.tradeType = '" + tradeType + "'");
        }
        sql.append(";");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        BigInteger id = (BigInteger) sqlQuery.uniqueResult();
        if (id != null) {
            return this.get(id.longValue());
        }else {
            return null;
        }
    }

    @Override
    public PayOrder getByOutTradeNo(String outTradeNo) {
        QueryCriteriaUtil q = new QueryCriteriaUtil(PayOrder.class);
        q.setSimpleCondition("outTradeNo", outTradeNo, QueryConstants.SimpleQueryMode.Equal);
        List<PayOrder> payOrders = this.filter(q, null, null);
        if (CollectionUtils.isNotEmpty(payOrders)){
            return payOrders.get(0);
        }else {
            return null;
        }
    }
}
