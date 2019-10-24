package me.own.learn.store.shoppingcart.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.store.shoppingcart.dao.ShoppingCartDao;
import me.own.learn.store.shoppingcart.dto.ShoppingCartDto;
import me.own.learn.store.shoppingcart.exception.ShoppintCartItemNotFoundException;
import me.own.learn.store.shoppingcart.exception.UnAuthorizedDeletionException;
import me.own.learn.store.shoppingcart.po.ShoppingCart;
import me.own.learn.store.shoppingcart.service.ShoppingCartService;
import me.own.learn.store.shoppingcart.vo.ShoppingCartVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    @Transactional
    public ShoppingCartVo create(ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCart = shoppingCartDao.getByProductId(shoppingCartDto.getCustomerId(), shoppingCartDto.getProductId());
        if (shoppingCart == null) {//没有则创建
            shoppingCart = Mapper.Default().map(shoppingCartDto, ShoppingCart.class);
            shoppingCart.onCreated();
            shoppingCartDao.create(shoppingCart);
            LOGGER.info("shopping car be created:{}", shoppingCart.getId());
        } else {//同一个商品重复添加增加数量
            shoppingCart.setQuantity(shoppingCart.getQuantity() + shoppingCartDto.getQuantity());
            shoppingCart.setModifyTime(new Date());
            shoppingCartDao.update(shoppingCart);
            LOGGER.info("shopping car be exist , increase quantity {}", shoppingCart.getQuantity());
        }
        return Mapper.Default().map(shoppingCart, ShoppingCartVo.class);
    }

    @Override
    public ShoppingCartVo update(ShoppingCartDto shoppingCartDto) {
        return null;
    }

    @Override
    @Transactional
    public void batchDelete(long customerId, List<Long> ids) {
        shoppingCartDao.batchDeleteShoppingCartItemByIds(customerId, ids);
    }


    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ShoppingCartVo> page(int pageNum, int pageSize, long customerId, List<Long> ids) {
        QueryCriteriaUtil queryCriteriaUtil = new QueryCriteriaUtil(ShoppingCart.class);
        queryCriteriaUtil.setDeletedFalseCondition();
        queryCriteriaUtil.setSimpleCondition("customerId", customerId + "", QueryConstants.SimpleQueryMode.Equal);
        if (CollectionUtils.isNotEmpty(ids)) {
            List<String> strings = new ArrayList<String>();
            CollectionUtils.collect(ids,
                    new Transformer() {
                        public Object transform(Object input) {
                            return String.valueOf((Long) input);
                        }
                    }, strings);
            queryCriteriaUtil.setComplexCondition("id", strings, QueryConstants.ComplexQueryMode.In);
        }
        PageQueryResult<ShoppingCart> result = shoppingCartDao.pageQuery(pageNum, pageSize, queryCriteriaUtil);
        return result.mapItems(ShoppingCartVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public double getAllShoppingCartItemPrice(long customerId, List<Long> ids) {
        return shoppingCartDao.getAllShoppingCartItemPrice(customerId, ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> getShoppingCartItemIdsByCustomerId(long customerId) {
        return shoppingCartDao.getCustomerShoppingCartItemIds(customerId);
    }

    @Override
    @Transactional
    public ShoppingCartVo updateQuantity(long customerId, long id, int quantity) {
        ShoppingCart shoppingCart = customerFetchCartItem(customerId, id);
        if (quantity >= 0) {
            shoppingCart.setQuantity(quantity);
            shoppingCart.onModified();
        }
        if (quantity == 0) {
            shoppingCart.onDelete();
        }
        shoppingCartDao.update(shoppingCart);
        return Mapper.Default().map(shoppingCart, ShoppingCartVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingCartVo getShoppingCartItemByProductId(long customerId, long productId) {
        ShoppingCart shoppingCart = shoppingCartDao.getByProductId(customerId, productId);
        if (shoppingCart == null || shoppingCart.getDeleted()) {
            return null;
        }
        return Mapper.Default().map(shoppingCart, ShoppingCartVo.class);
    }

    private ShoppingCart customerFetchCartItem(long customerId, long cartId) throws ShoppintCartItemNotFoundException, UnAuthorizedDeletionException {
        ShoppingCart shoppingCartItem = shoppingCartDao.get(cartId);
        if (shoppingCartItem == null || shoppingCartItem.getDeleted()) {
            throw new ShoppintCartItemNotFoundException();
        }
        if (shoppingCartItem.getCustomerId() != customerId) {
            throw new UnAuthorizedDeletionException();
        }
        return shoppingCartItem;
    }
}
