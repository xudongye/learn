package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.MallSubjectProductRelationDao;
import me.own.learn.mall.market.po.MallSubjectProductRelation;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 10:13
 */
@Repository
public class MallSubjectProductRelationDaoImpl extends BaseDaoImpl<MallSubjectProductRelation> implements MallSubjectProductRelationDao {
    @Override
    protected Class<MallSubjectProductRelation> getEntityClass() {
        return MallSubjectProductRelation.class;
    }
}
