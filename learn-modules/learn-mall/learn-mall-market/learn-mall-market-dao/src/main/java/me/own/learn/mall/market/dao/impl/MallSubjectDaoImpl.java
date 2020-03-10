package me.own.learn.mall.market.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.market.dao.MallSubjectDao;
import me.own.learn.mall.market.po.MallSubject;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/9 16:52
 */
@Repository
public class MallSubjectDaoImpl extends BaseDaoImpl<MallSubject> implements MallSubjectDao {
    @Override
    protected Class<MallSubject> getEntityClass() {
        return MallSubject.class;
    }
}
