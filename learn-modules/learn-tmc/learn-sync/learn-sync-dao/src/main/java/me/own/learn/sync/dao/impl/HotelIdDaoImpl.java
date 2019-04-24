package me.own.learn.sync.dao.impl;

import me.own.learn.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.sync.dao.HotelIdDao;
import me.own.learn.sync.po.HotelId;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/4/24 17:49
 */
@Repository
public class HotelIdDaoImpl extends BaseDaoImpl<HotelId> implements HotelIdDao {
    @Override
    protected Class<HotelId> getEntityClass() {
        return HotelId.class;
    }
}
