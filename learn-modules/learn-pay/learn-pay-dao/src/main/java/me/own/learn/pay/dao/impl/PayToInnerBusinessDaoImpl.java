package me.own.learn.pay.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.pay.dao.PayToInnerBusinessDao;
import me.own.learn.pay.po.PayToInnerBusiness;
import org.springframework.stereotype.Repository;

@Repository
public class PayToInnerBusinessDaoImpl extends BaseDaoImpl<PayToInnerBusiness> implements PayToInnerBusinessDao {
    @Override
    protected Class<PayToInnerBusiness> getEntityClass() {
        return PayToInnerBusiness.class;
    }
}
