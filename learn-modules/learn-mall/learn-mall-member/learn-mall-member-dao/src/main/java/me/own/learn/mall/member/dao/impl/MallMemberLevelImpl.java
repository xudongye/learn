package me.own.learn.mall.member.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.member.dao.MallMemberLevelDao;
import me.own.learn.mall.member.po.MallMemberLevel;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/9 17:56
 */
@Repository
public class MallMemberLevelImpl extends BaseDaoImpl<MallMemberLevel> implements MallMemberLevelDao {
    @Override
    protected Class<MallMemberLevel> getEntityClass() {
        return MallMemberLevel.class;
    }
}
