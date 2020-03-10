package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.SkuStockDao;
import me.own.learn.mall.product.po.SkuStock;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/9 16:07
 */
@Repository
public class SkuStockDaoImpl extends BaseDaoImpl<SkuStock> implements SkuStockDao {
    @Override
    protected Class<SkuStock> getEntityClass() {
        return SkuStock.class;
    }
}
