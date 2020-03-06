package me.own.learn.mall.authority.service.impl;

import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.authority.dao.MallResourceCategoryDao;
import me.own.learn.mall.authority.po.MallResourceCategory;
import me.own.learn.mall.aythority.service.MallResourceCategoryService;
import me.own.learn.mall.aythority.vo.MallResourceCategoryVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/6 15:08
 */
@Service
public class MallResourceCategoryServiceImpl implements MallResourceCategoryService {

    @Autowired
    private MallResourceCategoryDao mallResourceCategoryDao;

    @Override
    @Transactional(readOnly = true)
    public List<MallResourceCategoryVo> listAll() {
        List<MallResourceCategory> mallResourceCategories = mallResourceCategoryDao.getAll(null, new QueryOrder("sort", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(mallResourceCategories)) {
            return Mapper.Default().mapArray(mallResourceCategories, MallResourceCategoryVo.class);
        }
        return null;
    }
}
