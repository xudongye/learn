package me.own.learn.pubconfiguration.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.pubconfiguration.po.PubAccountMenu;

/**
 * @author yexudong
 * @date 2019/5/31 15:05
 */
public interface PubAccountMenuDao extends BaseDao<PubAccountMenu> {

    PubAccountMenu getByAppId(String appId);
}
