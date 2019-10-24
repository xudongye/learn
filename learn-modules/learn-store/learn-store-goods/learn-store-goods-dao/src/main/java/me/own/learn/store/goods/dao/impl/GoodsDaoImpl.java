package me.own.learn.store.goods.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.store.goods.dao.GoodsDao;
import me.own.learn.store.goods.po.Goods;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {
    @Override
    protected Class<Goods> getEntityClass() {
        return Goods.class;
    }
}
