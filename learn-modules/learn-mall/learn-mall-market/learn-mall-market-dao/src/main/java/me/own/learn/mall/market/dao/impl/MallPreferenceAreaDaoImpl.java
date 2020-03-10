package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.MallPreferenceAreaDao;
import me.own.learn.mall.market.po.MallPreferenceArea;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/9 17:32
 */
@Repository
public class MallPreferenceAreaDaoImpl extends BaseDaoImpl<MallPreferenceArea> implements MallPreferenceAreaDao {
    @Override
    protected Class<MallPreferenceArea> getEntityClass() {
        return MallPreferenceArea.class;
    }
}
