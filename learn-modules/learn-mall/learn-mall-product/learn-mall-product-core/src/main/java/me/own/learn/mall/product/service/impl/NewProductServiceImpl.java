package me.own.learn.mall.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.learn.mall.product.dao.NewProductDao;
import me.own.learn.mall.product.po.NewProduct;
import me.own.learn.mall.product.service.NewProductQueryCondition;
import me.own.learn.mall.product.service.NewProductService;
import me.own.learn.mall.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/5 13:37
 */
@Service
public class NewProductServiceImpl implements NewProductService {

    @Autowired
    private NewProductDao newProductDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ProductVo> page(int pageNum, int pageSize, NewProductQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(NewProduct.class);
        query.setDeletedFalseCondition();
        if (condition != null) {
            if (condition.getBrandId() != null) {
                query.setSimpleCondition("brandId", condition.getBrandId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getKeyword() != null) {
                query.setSimpleCondition("name", condition.getKeyword(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getProductCategoryId() != null) {
                query.setSimpleCondition("productCategoryId", condition.getProductCategoryId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getProductSn() != null) {
                query.setSimpleCondition("productSn", condition.getProductSn(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getPublishStatus() != null) {
                query.setSimpleCondition("publishStatus", condition.getPublishStatus() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getVerifyStatus() != null) {
                query.setSimpleCondition("verifyStatus", condition.getVerifyStatus() + "", QueryConstants.SimpleQueryMode.Equal);
            }
        }
        PageQueryResult<NewProduct> result = newProductDao.pageQuery(pageNum, pageSize, query);

        return result.mapItems(ProductVo.class);
    }
}
