package me.own.learn.admin.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.admin.dao.WebMenuDao;
import me.own.learn.admin.po.WebMenu;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/2 17:21
 */
@Repository
public class WebMenuDaoImpl extends BaseDaoImpl<WebMenu> implements WebMenuDao {
    @Override
    protected Class<WebMenu> getEntityClass() {
        return WebMenu.class;
    }
}
