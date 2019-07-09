package me.own.learn.store.category.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.store.category.dao.CategoryDao;
import me.own.learn.store.category.dto.CategoryDto;
import me.own.learn.store.category.exception.CategoryExistException;
import me.own.learn.store.category.exception.CategoryNotFoundException;
import me.own.learn.store.category.po.Category;
import me.own.learn.store.category.service.CategoryQueryCondition;
import me.own.learn.store.category.service.CategoryService;
import me.own.learn.store.category.vo.CategoryVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 15:19
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional
    public CategoryVo create(CategoryDto categoryDto) {
        if (categoryExist(categoryDto.getName())) {
            throw new CategoryExistException();
        }
        Category category = Mapper.Default().map(categoryDto, Category.class);
        category.setCreateTime(new Date());
        category.setDeleted(false);
        categoryDao.create(category);
        LOGGER.info("new a category {}", category.getName());
        return Mapper.Default().map(category, CategoryVo.class);
    }

    private boolean categoryExist(String name) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Category.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("name", name, QueryConstants.SimpleQueryMode.Equal);
        return categoryDao.getCount(query) > 0;
    }

    @Override
    @Transactional
    public CategoryVo update(CategoryDto categoryDto) {
        Category category = categoryDao.get(categoryDto.getId());
        if (category == null || category.getDeleted()) {
            throw new CategoryNotFoundException();
        }
        if (categoryDto.getName() != null) {
            if (!category.getName().equals(categoryDto.getName())) {
                if (categoryExist(categoryDto.getName())) {
                    throw new CategoryExistException();
                }
            }
            category.setName(categoryDto.getName());
        }
        if (categoryDto.getSortOrder() != null) {
            category.setSortOrder(categoryDto.getSortOrder());
        }
        category.setModifyTime(new Date());
        return Mapper.Default().map(category, CategoryVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult page(int pageNum, int pageSize, CategoryQueryCondition categoryQueryCondition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Category.class);
        query.setDeletedFalseCondition();
        if (categoryQueryCondition != null) {
            if (categoryQueryCondition.getName() != null) {
                query.setSimpleCondition("name", categoryQueryCondition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (categoryQueryCondition.getParent() != null) {
                query.setSimpleCondition("isParent", categoryQueryCondition.getParent() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (categoryQueryCondition.getParentId() != null) {
                query.setSimpleCondition("parentId", categoryQueryCondition.getParentId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("id");
        order.setOder(QueryOrder.ASC);
        PageQueryResult<Category> result = categoryDao.pageQuery(pageNum, pageSize, query, orders);
        return result.mapItems(CategoryVo.class);
    }

    @Override
    @Transactional
    public List<CategoryVo> listAll() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Category.class);
        query.setDeletedFalseCondition();
        List<Category> categories = categoryDao.filter(query, null, new QueryOrder("sortOrder", QueryOrder.ASC));
        if (CollectionUtils.isNotEmpty(categories)) {
            return Mapper.Default().mapArray(categories, CategoryVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryVo getById(long categoryId) {
        Category category = categoryDao.get(categoryId);
        if (category == null || category.getDeleted()) {
            throw new CategoryNotFoundException();
        }
        return Mapper.Default().map(category, CategoryVo.class);
    }
}
