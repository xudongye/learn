package me.own.learn.store.goods.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.goods.dao.GoodsCarryDao;
import me.own.learn.store.goods.po.GoodsCarry;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsCarryDaoImpl extends BaseDaoImpl<GoodsCarry> implements GoodsCarryDao {
    @Override
    protected Class<GoodsCarry> getEntityClass() {
        return GoodsCarry.class;
    }
}
