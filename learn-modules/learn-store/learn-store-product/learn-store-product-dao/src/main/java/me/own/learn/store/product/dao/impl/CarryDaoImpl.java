package me.own.learn.store.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.product.dao.CarryDao;
import me.own.learn.store.product.po.Carry;
import org.springframework.stereotype.Repository;

@Repository
public class CarryDaoImpl extends BaseDaoImpl<Carry> implements CarryDao {
    @Override
    protected Class<Carry> getEntityClass() {
        return Carry.class;
    }
}
