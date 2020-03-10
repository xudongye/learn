package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.FullReductionPriceDao;
import me.own.learn.mall.product.po.FullReductionPrice;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/9 10:05
 */
@Repository
public class FullReductionPriceDaoImpl extends BaseDaoImpl<FullReductionPrice> implements FullReductionPriceDao {
    @Override
    protected Class<FullReductionPrice> getEntityClass() {
        return FullReductionPrice.class;
    }
}
