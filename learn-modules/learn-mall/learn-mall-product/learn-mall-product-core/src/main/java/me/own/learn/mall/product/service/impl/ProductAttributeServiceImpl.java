package me.own.learn.mall.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.product.dao.ProductAttributeDao;
import me.own.learn.mall.product.dao.ProductCategoryAttributeRelationDao;
import me.own.learn.mall.product.dto.ProductAttributeDto;
import me.own.learn.mall.product.po.ProductAttribute;
import me.own.learn.mall.product.po.ProductCategoryAttributeRelation;
import me.own.learn.mall.product.service.ProductAttributeService;
import me.own.learn.mall.product.vo.ProductAttrInfoVo;
import me.own.learn.mall.product.vo.ProductAttributeVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:36
 */
@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

    @Autowired
    private ProductAttributeDao productAttributeDao;
    @Autowired
    private ProductCategoryAttributeRelationDao relationDao;

    @Override
    @Transactional
    public ProductAttributeVo create(ProductAttributeDto dto) {
        ProductAttribute productAttribute = Mapper.Default().map(dto, ProductAttribute.class);
        productAttributeDao.create(productAttribute);
        return Mapper.Default().map(productAttribute, ProductAttributeVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ProductAttributeVo> page(long cid, int pageNum, int pageSize, int type) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ProductAttribute.class);
        if (type != 0) {
            query.setSimpleCondition("type", type + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (cid != 0) {
            query.setSimpleCondition("productAttributeCategoryId", cid + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<ProductAttribute> result = productAttributeDao.pageQuery(pageNum, pageSize, query);

        return result.mapItems(ProductAttributeVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductAttrInfoVo> getProductAttrInfos(long productCategoryId) {
        List<ProductCategoryAttributeRelation> relations = relationDao.getByProductCategoryId(productCategoryId);
        if (CollectionUtils.isNotEmpty(relations)) {
            List<ProductAttrInfoVo> attrInfoVos = new ArrayList<>();
            ProductAttrInfoVo attrInfoVo = null;
            for (ProductCategoryAttributeRelation relation : relations) {
                attrInfoVo = new ProductAttrInfoVo();
                ProductAttribute productAttribute = productAttributeDao.get(relation.getProductAttributeId());
                attrInfoVo.setAttributeId(productAttribute.getId());
                attrInfoVo.setAttributeCategoryId(productAttribute.getProductAttributeCategoryId());
                attrInfoVos.add(attrInfoVo);
            }
            return attrInfoVos;
        }
        return null;
    }
}
