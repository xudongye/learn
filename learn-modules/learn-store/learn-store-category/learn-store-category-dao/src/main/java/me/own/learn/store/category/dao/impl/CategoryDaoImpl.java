package me.own.learn.store.category.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.product.po.Category;
import me.own.learn.store.category.dao.CategoryDao;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/6/12 15:07
 */
@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
