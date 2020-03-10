package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.LadderPriceDao;
import me.own.learn.mall.product.po.LadderPrice;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/9 9:45
 */
@Repository
public class LadderPriceDaoImpl extends BaseDaoImpl<LadderPrice> implements LadderPriceDao {
    @Override
    protected Class<LadderPrice> getEntityClass() {
        return LadderPrice.class;
    }
}
