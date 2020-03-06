package me.own.learn.mall.authority.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallResourceCategoryDao;
import me.own.learn.mall.authority.po.MallResourceCategory;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/6 15:07
 */
@Repository
public class MallResourceCategoryImpl extends BaseDaoImpl<MallResourceCategory> implements MallResourceCategoryDao {
    @Override
    protected Class<MallResourceCategory> getEntityClass() {
        return MallResourceCategory.class;
    }
}
