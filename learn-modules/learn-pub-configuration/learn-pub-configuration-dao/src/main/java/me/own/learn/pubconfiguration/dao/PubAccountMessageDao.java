package me.own.learn.pubconfiguration.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.pubconfiguration.po.PubAccountMessage;

/**
 * @author yexudong
 * @date 2019/5/31 11:33
 */
public interface PubAccountMessageDao extends BaseDao<PubAccountMessage> {

    PubAccountMessage getByAppIdAndEvent(String appId, String event);
}
