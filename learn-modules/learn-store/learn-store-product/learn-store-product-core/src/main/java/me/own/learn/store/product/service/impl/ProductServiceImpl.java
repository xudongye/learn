package me.own.learn.store.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.event.service.EventService;
import me.own.learn.event.service.message.product.ProductCreateMessage;
import me.own.learn.event.service.message.product.ProductDeleteMessage;
import me.own.learn.image.service.ImageService;
import me.own.learn.image.vo.ImageVo;
import me.own.learn.store.category.po.Category;
import me.own.learn.store.category.service.CategoryService;
import me.own.learn.store.category.vo.CategoryVo;
import me.own.learn.store.product.constant.ProductConstant;
import me.own.learn.store.product.dao.ProductPropertyItemDao;
import me.own.learn.store.product.dao.PropertyItemDao;
import me.own.learn.store.product.dao.ProductDao;
import me.own.learn.store.product.dto.ProductDto;
import me.own.learn.store.product.dto.ProductPropertyDto;
import me.own.learn.store.product.exception.*;
import me.own.learn.store.product.po.Product;
import me.own.learn.store.product.po.ProductPropertyItem;
import me.own.learn.store.product.po.PropertyItem;
import me.own.learn.store.product.service.ProductQueryCondition;
import me.own.learn.store.product.service.ProductService;
import me.own.learn.store.product.service.PropertyItemService;
import me.own.learn.store.product.service.SearchProductCondition;
import me.own.learn.store.product.vo.ProductDetailVo;
import me.own.learn.store.product.vo.ProductImageVo;
import me.own.learn.store.product.vo.ProductVo;
import me.own.learn.store.product.vo.PropertyItemValue;
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
 * @date 2019/7/3 13:40
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PropertyItemDao propertyItemDao;

    @Autowired
    private ProductPropertyItemDao productPropertyItemDao;

    @Autowired
    private PropertyItemService propertyItemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ImageService imageService;

    @Override
    @Transactional
    public ProductVo create(ProductDto productDto) {
        //商品创建时类目必选
        if (productDto.getCategory() == null) {
            throw new ProductCategoryCanNotNullException();
        }
        //商品类目必须指定
        if (productDto.getCategory().getId() == 0) {
            throw new ProductCategoryUnspecifiedException();
        }
        //不可选择父子类目直接绑定商品
        CategoryVo categoryVo = categoryService.getById(productDto.getCategory().getId());
        if (categoryVo.getParent() == null) {
            throw new ProductCanNotBindParentCategoryException();
        }
        //product image must add
        if (CollectionUtils.isEmpty(productDto.getImages())) {
            throw new ProductImageMustAddException();
        }
        Product product = Mapper.Default().map(productDto, Product.class);
        product.setCreateTime(new Date());
        product.setDeleted(false);
        //上下架状态
        product.setStatus(productDto.getSoldStatus().getCode());
        product.setCategory(Mapper.Default().map(productDto.getCategory(), Category.class));
        LOGGER.info("create new product {} in category {}", product.getId(), categoryVo.getName());
        productDao.create(product);
        String skuNo = SkuNoGenerator.generate();
        onTypeInProductNum(product.getId(), skuNo);
        eventService.enqueue(EventService.EventName.ProductEvent.PRODUCT_CREATE,
                new ProductCreateMessage(skuNo, categoryVo.getName(), product.getName(), product.getId(), ""));
        return Mapper.Default().map(product, ProductVo.class);
    }

    private void onTypeInProductNum(long productId, String skuNo) {
        ProductPropertyItem productPropertyItem = new ProductPropertyItem();
        productPropertyItem.setEnable(true);
        Product product = new Product();
        product.setId(productId);
        productPropertyItem.setProduct(product);
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(1L);
        productPropertyItem.setPropertyItem(propertyItem);
        productPropertyItem.setPropertyValue(skuNo);
        productPropertyItemDao.create(productPropertyItem);
        LOGGER.info("generator product {} skuNo {}", productId, skuNo);
    }

    @Override
    @Transactional
    public ProductDetailVo bindProperty(long productId, List<ProductPropertyDto> propertyDtos) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        ProductDetailVo productVo = Mapper.Default().map(product, ProductDetailVo.class);
        List<PropertyItemValue> propertyItemValues = new ArrayList<>();
        for (ProductPropertyDto productPropertyDto : propertyDtos) {
            ProductPropertyItem productPropertyItem = productPropertyItemDao.getByProductIdAndPropertyId(productId, productPropertyDto.getPropertyId());

            PropertyItem propertyItem = null;
            if (productPropertyItem == null) {
                propertyItem = propertyItemDao.get(productPropertyDto.getPropertyId());
                //绑定产品属性
                productPropertyItem = new ProductPropertyItem();
                productPropertyItem.setProduct(product);
                productPropertyItem.setPropertyItem(propertyItem);
                productPropertyItem.setEnable(true);
                productPropertyItemDao.create(productPropertyItem);
            } else {
                propertyItem = productPropertyItem.getPropertyItem();
                LOGGER.warn("product {} property {}  already set in", productVo.getName(), propertyItem.getName());
            }
            //属性值不为空，直接set
            if (productPropertyDto.getValue() != null) {
                productPropertyItem.setPropertyValue(productPropertyDto.getValue());
                productPropertyItemDao.update(productPropertyItem);
            }
            PropertyItemValue propertyItemValue = new PropertyItemValue();
            propertyItemValue.setPropertyId(productPropertyItem.getPropertyItem().getId());
            propertyItemValue.setProperty(productPropertyItem.getPropertyItem().getName());
            propertyItemValue.setValue(productPropertyItem.getPropertyValue());
            propertyItemValues.add(propertyItemValue);
        }
        productVo.setPropertyItems(propertyItemValues);
        return productVo;
    }

    @Override
    @Transactional
    public void setPropertyValue(long productId, List<ProductPropertyDto> propertyDtos) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        for (ProductPropertyDto propertyDto : propertyDtos) {
            productPropertyItemDao.setValue(productId, propertyDto.getPropertyId(), propertyDto.getValue());
            LOGGER.info("product {} set property value {}", product.getName(), propertyDto.getValue());
        }
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
        if (productDto.getBrandName() != null) {
            product.setBrandName(productDto.getBrandName());
        }
        if (productDto.getHitCount() != null) {
            product.setHitCount(productDto.getHitCount());
        }
        if (productDto.getSaleCount() != null) {
            product.setSaleCount(productDto.getSaleCount());
        }
        if (productDto.getCategory() != null || product.getCategory().getId() != productDto.getCategory().getId()) {
            CategoryVo categoryVo = categoryService.getById(productDto.getCategory().getId());
            if (categoryVo.getParent() == null) {
                throw new ProductCanNotBindParentCategoryException();
            }
            product.setCategory(Mapper.Default().map(productDto.getCategory(), Category.class));
        }
        product.setModifyTime(new Date());
        productDao.update(product);
        eventService.enqueue(EventService.EventName.ProductEvent.PRODUCT_UPDATE,
                new ProductCreateMessage(productPropertyItemDao.getSkuNoByProductId(product.getId()), product.getCategory().getName(), product.getName(), product.getId(), product.getBrandName()));
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
//        Category category = product.getCategory();
        //罗列产品属性
        detailVo.setPropertyItems(propertyItemService.listGroupByProductId(productId));
        //商品图片
        List<ImageVo> imageVos = imageService.getByProductId(productId);
        if (CollectionUtils.isNotEmpty(imageVos)) {
            detailVo.setImages(Mapper.Default().mapArray(imageVos, ProductImageVo.class));
        }
        return detailVo;
    }

    @Override
    @Transactional
    public void delete(long productId) {
        Product product = productDao.get(productId);
        if (product == null || product.getDeleted()) {
            throw new ProductNotFoundException();
        }
        product.setDeleted(true);
        eventService.enqueue(EventService.EventName.ProductEvent.PRODUCT_DELETE,
                new ProductDeleteMessage(productPropertyItemDao.getSkuNoByProductId(productId)));
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
            if (condition.getSoldStatus() != null) {
                query.setSimpleCondition("status", condition.getSoldStatus().getCode() + "", QueryConstants.SimpleQueryMode.Equal);
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

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ProductVo> searchProduct(int pageNum, int pageSize, SearchProductCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Product.class);
        query.setDeletedFalseCondition();
        List<QueryOrder> queryOrders = new ArrayList<>();
        //默认时间倒序
        queryOrders.add(new QueryOrder("createTime", QueryOrder.DESC));
        //上架商品
        query.setSimpleCondition("status", ProductConstant.SoldStatus.putaway.getCode() + "", QueryConstants.SimpleQueryMode.Equal);
        if (condition != null) {
            if (condition.getCategoryId() != null) {
                query.setSimpleCondition("category.id", condition.getCategoryId() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getKeyword() != null) {
                //TODO 关键词搜索

            }
            //价格排序
            if (condition.getPriceSort() != null) {
                QueryOrder queryOrder = new QueryOrder();
                queryOrder.setColumnName("originalPrice");
                //从高到低
                if (condition.getPriceSort().getCode() == ProductConstant.PriceSort.hTol.getCode()) {
                    queryOrder.setOder(QueryOrder.DESC);
                } else {
                    queryOrder.setOder(QueryOrder.ASC);
                }
                queryOrders.add(queryOrder);
            }
        }

        PageQueryResult<Product> result = productDao.pageQuery(pageNum, pageSize, query, queryOrders);
        PageQueryResult<ProductVo> voPageQueryResult = result.mapItems(ProductVo.class);
        for (Product item : result.getItems()) {

        }
        return result.mapItems(ProductVo.class);
    }
}
