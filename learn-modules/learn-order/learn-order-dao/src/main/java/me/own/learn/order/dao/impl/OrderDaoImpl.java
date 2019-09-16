package me.own.learn.order.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.order.dao.OrderDao;
import me.own.learn.order.po.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {
    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }
}
