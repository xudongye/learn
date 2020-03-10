package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.HomeAdvertiseDao;
import me.own.learn.mall.market.po.HomeAdvertise;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 15:26
 */
@Repository
public class HomeAdvertiseDaoImpl extends BaseDaoImpl<HomeAdvertise> implements HomeAdvertiseDao {
    @Override
    protected Class<HomeAdvertise> getEntityClass() {
        return HomeAdvertise.class;
    }
}
