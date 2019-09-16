package me.own.learn.order.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.order.dao.OrderAddressDao;
import me.own.learn.order.po.OrderAddress;
import org.springframework.stereotype.Repository;

@Repository
public class OrderAddressDaoImpl extends BaseDaoImpl<OrderAddress> implements OrderAddressDao {
    @Override
    protected Class<OrderAddress> getEntityClass() {
        return OrderAddress.class;
    }
}
