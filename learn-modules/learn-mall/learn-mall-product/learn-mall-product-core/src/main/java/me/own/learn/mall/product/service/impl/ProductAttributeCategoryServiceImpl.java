package me.own.learn.mall.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.product.dao.ProductAttributeCategoryDao;
import me.own.learn.mall.product.dao.ProductAttributeDao;
import me.own.learn.mall.product.po.ProductAttribute;
import me.own.learn.mall.product.po.ProductAttributeCategory;
import me.own.learn.mall.product.service.ProductAttributeCategoryService;
import me.own.learn.mall.product.vo.ProductAttributeCategoryItemVo;
import me.own.learn.mall.product.vo.ProductAttributeCategoryVo;
import me.own.learn.mall.product.vo.ProductAttributeVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:11
 */
@Service
public class ProductAttributeCategoryServiceImpl implements ProductAttributeCategoryService {

    @Autowired
    private ProductAttributeCategoryDao productAttributeCategoryDao;
    @Autowired
    private ProductAttributeDao productAttributeDao;

    @Override
    @Transactional
    public ProductAttributeCategoryVo create(String name) {
        ProductAttributeCategory productAttributeCategory = new ProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategoryDao.create(productAttributeCategory);
        return Mapper.Default().map(productAttributeCategory, ProductAttributeCategoryVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ProductAttributeCategoryVo> page(int pageNum, int pageSize) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ProductAttributeCategory.class);
        PageQueryResult<ProductAttributeCategory> result = productAttributeCategoryDao.pageQuery(pageNum, pageSize, query);

        return result.mapItems(ProductAttributeCategoryVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductAttributeCategoryItemVo> withAttr() {

        List<ProductAttributeCategory> pac = productAttributeCategoryDao.getAll(null, null);
        if (CollectionUtils.isNotEmpty(pac)) {
            List<ProductAttributeCategoryItemVo> itemVos = Mapper.Default().mapArray(pac, ProductAttributeCategoryItemVo.class);
            QueryCriteriaUtil query = new QueryCriteriaUtil(ProductAttribute.class);
            query.setSimpleCondition("type", 1 + "", QueryConstants.SimpleQueryMode.Equal);
            for (ProductAttributeCategoryItemVo itemVo : itemVos) {
                query.setSimpleCondition("productAttributeCategoryId", itemVo.getId() + "", QueryConstants.SimpleQueryMode.Equal);
                List<ProductAttribute> productAttributes = productAttributeDao.filter(query, null, null);
                if (CollectionUtils.isNotEmpty(productAttributes)) {
                    itemVo.setProductAttributeList(Mapper.Default().mapArray(productAttributes, ProductAttributeVo.class));
                }
            }
            return itemVos;
        }
        return null;
    }
}
