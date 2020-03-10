package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeBrandDao;
import me.own.learn.mall.market.po.HomeBrand;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 17:28
 */
@Repository
public class HomeBrandDaoImpl extends BaseDaoImpl<HomeBrand> implements HomeBrandDao {
    @Override
    protected Class<HomeBrand> getEntityClass() {
        return HomeBrand.class;
    }
}
