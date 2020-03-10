package me.own.learn.mall.product.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.product.dao.MemberPriceDao;
import me.own.learn.mall.product.po.MemberPrice;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/8 20:33
 */
@Repository
public class MemberPriceDaoImpl extends BaseDaoImpl<MemberPrice> implements MemberPriceDao {
    @Override
    protected Class<MemberPrice> getEntityClass() {
        return MemberPrice.class;
    }
}
