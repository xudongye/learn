package me.own.learn.image.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.image.dao.ImageDao;
import me.own.learn.image.dto.ImageDto;
import me.own.learn.image.exception.ImageNotFoundException;
import me.own.learn.image.po.Image;
import me.own.learn.image.service.ImageQueryCondition;
import me.own.learn.image.service.ImageService;
import me.own.learn.image.vo.ImageVo;
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
 * @date 2019/7/3 14:14
 */
@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private ImageDao imageDao;

    @Override
    @Transactional
    public ImageVo save(ImageDto imageDto) {

        Image image = Mapper.Default().map(imageDto, Image.class);
        image.setCreateTime(new Date());
        image.setType(imageDto.getImageType().getCode());
        image.setDeleted(false);
        imageDao.create(image);
        LOGGER.info("upload new image {} type {} url {}", image.getId(), image.getType(), image.getUrl());
        return Mapper.Default().map(image, ImageVo.class);
    }

    @Override
    @Transactional
    public void delete(long imageId) {
        Image image = imageDao.get(imageId);
        if (image == null || image.getDeleted()) {
            throw new ImageNotFoundException();
        }
        image.setDeleted(true);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<ImageVo> page(int pageNum, int pageSize, ImageQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Image.class);
        query.setDeletedFalseCondition();

        if (condition != null) {
            if (condition.getImageType().getCode() != 0) {
                query.setSimpleCondition("type", condition.getImageType().getCode() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getProductId() != 0) {
                query.setSimpleCondition("productId", condition.getProductId() + "", QueryConstants.SimpleQueryMode.Equal);
            }

        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("createTime");
        order.setOder(QueryOrder.DESC);
        PageQueryResult<Image> result = imageDao.pageQuery(pageNum, pageSize, query, orders);

        return result.mapItems(ImageVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageVo> getByProductId(long productId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Image.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("productId", productId + "", QueryConstants.SimpleQueryMode.Equal);
        List<Image> images = imageDao.filter(query, null, null);
        if (CollectionUtils.isNotEmpty(images)) {
            return Mapper.Default().mapArray(images, ImageVo.class);
        }
        return null;
    }
}
