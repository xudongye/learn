package me.own.learn.mall.authority.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.mall.authority.po.MallMenu;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/2 17:21
 */
public interface MallMenuDao extends BaseDao<MallMenu> {

    List<MallMenu> getByAdminId(long adminId);
}
