package me.own.learn.mall.authority.dao.impl;
import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.authority.dao.MallResourceDao;
import me.own.learn.mall.authority.po.MallResource;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/6 10:49
 */
@Repository
public class MallResourceDaoImpl extends BaseDaoImpl<MallResource> implements MallResourceDao {
    @Override
    protected Class<MallResource> getEntityClass() {
        return MallResource.class;
    }
}
