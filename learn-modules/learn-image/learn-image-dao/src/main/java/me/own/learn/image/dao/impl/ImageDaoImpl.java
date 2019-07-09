package me.own.learn.image.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.image.dao.ImageDao;
import me.own.learn.image.po.Image;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/7/3 13:59
 */
@Repository
public class ImageDaoImpl extends BaseDaoImpl<Image> implements ImageDao {
    @Override
    protected Class<Image> getEntityClass() {
        return Image.class;
    }
}
