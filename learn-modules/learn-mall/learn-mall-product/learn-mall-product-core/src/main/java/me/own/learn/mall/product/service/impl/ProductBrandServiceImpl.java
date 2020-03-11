package me.own.learn.mall.product.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.product.dao.ProductBrandDao;
import me.own.learn.mall.product.dto.ProductBrandDto;
import me.own.learn.mall.product.exception.ProductBrandNotFoundException;
import me.own.learn.mall.product.po.ProductBrand;
import me.own.learn.mall.product.service.ProductBrandService;
import me.own.learn.mall.product.vo.ProductBrandVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


/**
 * @author: yexudong
 * @Date: 2020/3/3 11:36
 */
@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    private ProductBrandDao productBrandDao;

    @Override
    @Transactional
    public ProductBrandVo create(ProductBrandDto dto) {
        ProductBrand productBrand = Mapper.Default().map(dto, ProductBrand.class);
        productBrandDao.create(productBrand);
        return Mapper.Default().map(productBrand, ProductBrandVo.class);
    }

    @Override
    @Transactional
    public ProductBrandVo updateById(long id, ProductBrandDto dto) {
        ProductBrand productBrand = productBrandDao.get(id);
        if (dto.getBigPic() == null) {
            throw new ProductBrandNotFoundException();
        }
        BeanUtils.copyProperties(dto, productBrand);
        productBrandDao.update(productBrand);
        return Mapper.Default().map(productBrand, ProductBrandVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ProductBrandVo> page(int pageNum, int pageSize, String brandName) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ProductBrand.class);
        if (brandName != null) {
            query.setSimpleCondition("name", brandName, QueryConstants.SimpleQueryMode.Like);
        }
        PageQueryResult<ProductBrand> result = productBrandDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(ProductBrandVo.class);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ProductBrandVo getById(long id) {
        ProductBrand productBrand = productBrandDao.get(id);
        if (productBrand == null) {
            throw new ProductBrandNotFoundException();
        }
        return Mapper.Default().map(productBrand, ProductBrandVo.class);
    }
}
