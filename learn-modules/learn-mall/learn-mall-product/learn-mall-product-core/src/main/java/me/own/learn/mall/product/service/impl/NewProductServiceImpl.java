package me.own.learn.mall.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.MallPreferenceAreaProductRelationDao;
import me.own.learn.mall.market.dao.MallSubjectProductRelationDao;
import me.own.learn.mall.market.po.MallPreferenceAreaProductRelation;
import me.own.learn.mall.market.po.MallSubjectProductRelation;
import me.own.learn.mall.product.dao.*;
import me.own.learn.mall.product.dto.NewProductDto;
import me.own.learn.mall.product.dto.ProductDto;
import me.own.learn.mall.product.dto.ProductMemberPriceDto;
import me.own.learn.mall.product.po.*;
import me.own.learn.mall.product.service.NewProductQueryCondition;
import me.own.learn.mall.product.service.NewProductService;
import me.own.learn.mall.product.vo.ProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/5 13:37
 */
@Service
public class NewProductServiceImpl implements NewProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewProductServiceImpl.class);
    @Autowired
    private NewProductDao newProductDao;
    @Autowired
    private MemberPriceDao memberPriceDao;
    @Autowired
    private LadderPriceDao ladderPriceDao;
    @Autowired
    private SkuStockDao skuStockDao;
    @Autowired
    private ProductAttributeValueDao productAttributeValueDao;
    @Autowired
    private FullReductionPriceDao fullReductionPriceDao;
    @Autowired
    private MallSubjectProductRelationDao subjectProductRelationDao;
    @Autowired
    private MallPreferenceAreaProductRelationDao preferenceAreaProductRelationDao;

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

    @Override
    @Transactional
    public ProductVo create(NewProductDto dto) {

        NewProduct product = Mapper.Default().map(dto, NewProduct.class);
        product.setDeleted(false);
        newProductDao.create(product);
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceDao,
                Mapper.Default().mapArray(dto.getMemberPriceList(), MemberPrice.class), productId);
        //阶梯价格
        relateAndInsertList(ladderPriceDao,
                Mapper.Default().mapArray(dto.getProductLadderList(), LadderPrice.class), productId);
        //满减价格
        relateAndInsertList(fullReductionPriceDao,
                Mapper.Default().mapArray(dto.getProductFullReductionList(), FullReductionPrice.class), productId);
        //处理sku编码
        handleSkuStockCode(Mapper.Default().mapArray(dto.getSkuStockList(), SkuStock.class), productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, Mapper.Default().mapArray(dto.getSkuStockList(), SkuStock.class), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao,
                Mapper.Default().mapArray(dto.getProductAttributeValueList(), ProductAttributeValue.class), productId);
        // 关联专题
        relateAndInsertList(subjectProductRelationDao,
                Mapper.Default().mapArray(dto.getSubjectProductRelationList(), MallSubjectProductRelation.class), productId);
        //关联优选
        relateAndInsertList(preferenceAreaProductRelationDao,
                Mapper.Default().mapArray(dto.getPrefrenceAreaProductRelationList(), MallPreferenceAreaProductRelation.class), productId);
        return Mapper.Default().map(product, ProductVo.class);
    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object o : dataList) {
                Method setId = o.getClass().getMethod("setId", Long.class);
                setId.invoke(o, (Long) null);
                Method setProductId = o.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(o, productId);
            }
            Method create = dao.getClass().getMethod("batchCreate", List.class);
            create.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品时出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    private void handleSkuStockCode(List<SkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        for (int i = 0; i < skuStockList.size(); i++) {
            SkuStock skuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }
}
