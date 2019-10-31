package me.own.learn.store.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.product.dao.PropertyItemDao;
import me.own.learn.store.product.po.PropertyItem;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyItemDaoImpl extends BaseDaoImpl<PropertyItem> implements PropertyItemDao {
    @Override
    protected Class<PropertyItem> getEntityClass() {
        return PropertyItem.class;
    }
}
