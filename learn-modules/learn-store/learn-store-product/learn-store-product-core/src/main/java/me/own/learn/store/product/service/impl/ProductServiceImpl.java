package me.own.learn.store.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.store.category.service.CategoryService;
import me.own.learn.store.category.vo.CategoryVo;
import me.own.learn.store.product.constant.ProductConstant;
import me.own.learn.store.product.dao.ProductDao;
import me.own.learn.store.product.dto.ProductDto;
import me.own.learn.store.product.exception.ProductNotFoundException;
import me.own.learn.store.product.po.Product;
import me.own.learn.store.product.service.ProductQueryCondition;
import me.own.learn.store.product.service.ProductService;
import me.own.learn.store.product.vo.ProductCategoryVo;
import me.own.learn.store.product.vo.ProductDetailVo;
import me.own.learn.store.product.vo.ProductVo;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/7/3 13:40
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public ProductVo create(ProductDto productDto) {
        CategoryVo categoryVo = categoryService.getById(productDto.getCategoryId());
        Product product = Mapper.Default().map(productDto, Product.class);
        product.setCreateTime(new Date());
        product.setDeleted(false);
        productDao.create(product);
        LOGGER.info("create new product {} in category {}", product.getId(), categoryVo.getName());
        return Mapper.Default().map(product, ProductVo.class);
    }

    @Override
    @Transactional
    public ProductVo update(ProductDto productDto) {
        Product product = productDao.get(productDto.getId());
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        product.setModifyTime(new Date());
        return Mapper.Default().map(product, ProductVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVo getById(long productId) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        return Mapper.Default().map(product, ProductVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetailVo getByProductId(long productId) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        ProductDetailVo detailVo = Mapper.Default().map(product, ProductDetailVo.class);
        if (detailVo.getCategoryId() != null) {
            CategoryVo categoryVo = categoryService.getById(detailVo.getCategoryId());
            detailVo.setCategory(Mapper.Default().map(categoryVo, ProductCategoryVo.class));
        }
        return detailVo;
    }

    @Override
    @Transactional
    public void soldOut(long productId) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        LOGGER.info("product {} been soldOut!", product.getName());
    }

    @Override
    @Transactional
    public void putSold(List<Long> productIds, int status) {
        productDao.putSold(productIds, status);
        LOGGER.info("batch update product {} status {}", productIds, status);
    }

    @Override
    @Transactional
    public void delete(long productId) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        product.setDeleted(true);
    }

    @Override
    @Transactional
    public void reduceInventory(long productId, int count) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }

        product.setModifyTime(new Date());
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ProductVo> page(int pageNum, int pageSize, ProductQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Product.class);
        query.setDeletedFalseCondition();
        if (condition != null) {
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getDescription() != null) {
                query.setSimpleCondition("description", condition.getDescription(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getTitle() != null) {
                query.setSimpleCondition("title", condition.getTitle(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getStatus() != null) {
                query.setSimpleCondition("status", condition.getStatus().getCode() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getCategoryId() != null) {
                query.setSimpleCondition("categoryId", condition.getCategoryId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            //todo 时间搜索
        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("id");
        order.setOder(QueryOrder.ASC);
        orders.add(order);
        PageQueryResult<Product> result = productDao.pageQuery(pageNum, pageSize, query, orders);

        return result.mapItems(ProductVo.class);
    }
}
