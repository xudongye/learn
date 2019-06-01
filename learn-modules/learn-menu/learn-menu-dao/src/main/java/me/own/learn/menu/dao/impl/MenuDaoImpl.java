package me.own.learn.menu.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.menu.dao.MenuDao;
import me.own.learn.menu.po.Menu;
import org.springframework.stereotype.Repository;

/**
 * @author:simonye
 * @date 22:26 2019/6/1
 **/
@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {
    @Override
    protected Class<Menu> getEntityClass() {
        return Menu.class;
    }
}
