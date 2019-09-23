package me.own.learn.order.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.order.dao.OrderDetailDao;
import me.own.learn.order.po.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailDaoImpl extends BaseDaoImpl<OrderDetail> implements OrderDetailDao {
    @Override
    protected Class<OrderDetail> getEntityClass() {
        return OrderDetail.class;
    }
}
