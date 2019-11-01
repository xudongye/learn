package me.own.learn.store.product.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.store.product.dao.ProductPropertyItemDao;
import me.own.learn.store.product.dao.PropertyItemDao;
import me.own.learn.store.product.dto.PropertyItemDto;
import me.own.learn.store.product.exception.CarryNotFoundException;
import me.own.learn.store.product.po.ProductPropertyItem;
import me.own.learn.store.product.po.PropertyItem;
import me.own.learn.store.product.service.PropertyItemService;
import me.own.learn.store.product.vo.PropertyItemValue;
import me.own.learn.store.product.vo.PropertyItemVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PropertyItemServiceImpl implements PropertyItemService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyItemServiceImpl.class);

    @Autowired
    private PropertyItemDao propertyItemDao;

    @Autowired
    private ProductPropertyItemDao productPropertyItemDao;

    @Override
    @Transactional
    public PropertyItemVo create(PropertyItemDto propertyItemDto) {
        PropertyItem carry = Mapper.Default().map(propertyItemDto, PropertyItem.class);
        carry.setCreateTime(new Date());
        carry.setDeleted(false);
        propertyItemDao.create(carry);
        LOGGER.info("create new property name {}", carry.getName());
        return Mapper.Default().map(carry, PropertyItemVo.class);
    }

    @Override
    public PropertyItemVo update(PropertyItemDto propertyItemDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        PropertyItem carry = propertyItemDao.get(id);
        if (carry == null || carry.getDeleted()) {
            throw new CarryNotFoundException();
        }
        carry.setDeleted(true);
        carry.setModifyTime(new Date());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PropertyItemValue> listGroupByProductId(long productId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(ProductPropertyItem.class);
        query.setSimpleCondition("product.id", productId + "", QueryConstants.SimpleQueryMode.Equal);
        List<ProductPropertyItem> properties = productPropertyItemDao.filter(query, null, null);
        if (CollectionUtils.isNotEmpty(properties)) {
            List<PropertyItemValue> propertyItemValues = new ArrayList<>();
            PropertyItemValue propertyItemValue = null;
            for (ProductPropertyItem property : properties) {
                propertyItemValue = new PropertyItemValue();
                propertyItemValue.setPropertyId(property.getPropertyItem().getId());
                propertyItemValue.setProperty(property.getPropertyItem().getName());
                propertyItemValue.setValue(property.getPropertyValue());
                propertyItemValues.add(propertyItemValue);
            }
            return propertyItemValues;
        }
        return null;
    }
}
