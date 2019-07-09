package me.own.learn.image.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.image.dto.ImageDto;
import me.own.learn.image.vo.ImageVo;

/**
 * @author yexudong
 * @date 2019/7/3 14:03
 */
public interface ImageService {

    ImageVo save(ImageDto imageDto);

    void delete(long imageId);

    PageQueryResult<ImageVo> page(int pageNum, int pageSize, ImageQueryCondition condition);
}
