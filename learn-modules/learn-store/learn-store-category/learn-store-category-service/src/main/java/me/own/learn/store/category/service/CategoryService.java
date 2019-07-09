package me.own.learn.store.category.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.store.category.dto.CategoryDto;
import me.own.learn.store.category.vo.CategoryVo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 15:16
 */
public interface CategoryService {

    CategoryVo create(CategoryDto categoryDto);

    CategoryVo update(CategoryDto categoryDto);

    PageQueryResult page(int pageNum, int pageSize, CategoryQueryCondition categoryQueryCondition);

    List<CategoryVo> listAll();

    CategoryVo getById(long categoryId);
}
