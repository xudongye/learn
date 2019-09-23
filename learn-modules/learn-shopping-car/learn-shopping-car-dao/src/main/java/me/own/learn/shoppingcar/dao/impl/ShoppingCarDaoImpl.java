package me.own.learn.shoppingcar.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.shoppingcar.dao.ShoppingCarDao;
import me.own.learn.shoppingcar.po.ShoppingCar;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCarDaoImpl extends BaseDaoImpl<ShoppingCar> implements ShoppingCarDao {
    @Override
    protected Class<ShoppingCar> getEntityClass() {
        return ShoppingCar.class;
    }
}
