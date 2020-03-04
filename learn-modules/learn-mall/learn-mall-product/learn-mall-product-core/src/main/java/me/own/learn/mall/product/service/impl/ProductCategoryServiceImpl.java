package me.own.learn.mall.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.product.dao.ProductCategoryAttributeRelationDao;
import me.own.learn.mall.product.dao.ProductCategoryDao;

import me.own.learn.mall.product.dto.ProductCategoryDto;

import me.own.learn.mall.product.exception.ProductCategoryNotFoundException;
import me.own.learn.mall.product.po.ProductCategory;
import me.own.learn.mall.product.po.ProductCategoryAttributeRelation;
import me.own.learn.mall.product.service.ProductCategoryService;

import me.own.learn.mall.product.vo.ProductCategoryVo;
import me.own.learn.mall.product.vo.ProductCategoryWithChildrenItemVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: yexudong
 * @Date: 2020/3/4 9:43
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductCategoryAttributeRelationDao productCategoryAttributeRelationDao;

    @Override
    @Transactional
    public ProductCategoryVo create(ProductCategoryDto dto) {
        ProductCategory productCategory = Mapper.Default().map(dto, ProductCategory.class);
        int level = 0;
        if (dto.getParentId() != 0) {
            ProductCategory parent = productCategoryDao.get(dto.getParentId());
            if (parent != null) {
                level = parent.getLevel() + 1;
            }
        }
        productCategory.setLevel(level);
        productCategoryDao.create(productCategory);
        if (CollectionUtils.isNotEmpty(dto.getProductAttributeIdList())) {
            insertRelations(productCategory.getId(), dto.getProductAttributeIdList());
        }
        return Mapper.Default().map(productCategory, ProductCategoryVo.class);
    }

    @Override
    @Transactional
    public ProductCategoryVo updateById(long id, ProductCategoryDto dto) {
        ProductCategory productCategory = productCategoryDao.get(id);
        if (productCategory == null) {
            throw new ProductCategoryNotFoundException();
        }
        BeanUtils.copyProperties(dto, productCategory);
        if (CollectionUtils.isNotEmpty(dto.getProductAttributeIdList())) {
            insertRelations(id, dto.getProductAttributeIdList());
        }
        productCategoryDao.update(productCategory);
        return Mapper.Default().map(productCategory, ProductCategoryVo.class);
    }

    private void insertRelations(long productCategoryId, List<Long> productAttributeIdList) {
        ProductCategoryAttributeRelation attributeRelation = null;
        for (Long productAttributeId : productAttributeIdList) {
            attributeRelation = new ProductCategoryAttributeRelation();
            attributeRelation.setProductCategoryId(productCategoryId);
            attributeRelation.setProductAttributeId(productAttributeId);
            productCategoryAttributeRelationDao.create(attributeRelation);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ProductCategoryVo> page(int pageNum, int pageSize, long parentId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ProductCategory.class);

        query.setSimpleCondition("parentId", parentId + "", QueryConstants.SimpleQueryMode.Equal);
        PageQueryResult<ProductCategory> result = productCategoryDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(ProductCategoryVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductCategoryVo getById(long id) {
        ProductCategory productCategory = productCategoryDao.get(id);
        if (productCategory == null) {
            throw new ProductCategoryNotFoundException();
        }
        return Mapper.Default().map(productCategory, ProductCategoryVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategoryWithChildrenItemVo> listWithChildren() {

        List<ProductCategory> parents = productCategoryDao.listByParentId(0);
        if (CollectionUtils.isNotEmpty(parents)) {
            List<ProductCategoryWithChildrenItemVo> itemVos = Mapper.Default().mapArray(parents, ProductCategoryWithChildrenItemVo.class);
            for (ProductCategoryWithChildrenItemVo itemVo : itemVos) {
                List<ProductCategory> childrens = productCategoryDao.listByParentId(itemVo.getId());
                if (CollectionUtils.isNotEmpty(childrens)) {
                    itemVo.setChildren(Mapper.Default().mapArray(childrens, ProductCategoryVo.class));
                }
            }
            return itemVos;
        }
        return null;
    }
}
