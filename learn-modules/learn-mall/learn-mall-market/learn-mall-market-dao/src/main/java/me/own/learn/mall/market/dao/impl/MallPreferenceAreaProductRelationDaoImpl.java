package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.MallPreferenceAreaProductRelationDao;
import me.own.learn.mall.market.po.MallPreferenceAreaProductRelation;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 10:24
 */
@Repository
public class MallPreferenceAreaProductRelationDaoImpl extends BaseDaoImpl<MallPreferenceAreaProductRelation> implements
        MallPreferenceAreaProductRelationDao {
    @Override
    protected Class<MallPreferenceAreaProductRelation> getEntityClass() {
        return MallPreferenceAreaProductRelation.class;
    }
}
