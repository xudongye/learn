package me.own.learn.mall.member.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.mall.member.dao.MallMemberDao;
import me.own.learn.mall.member.po.MallMember;
import org.springframework.stereotype.Repository;

/**
 * @author: yexudong
 * @Date: 2020/3/10 11:51
 */
@Repository
public class MallMemberDaoImpl extends BaseDaoImpl<MallMember> implements MallMemberDao {
    @Override
    protected Class<MallMember> getEntityClass() {
        return MallMember.class;
    }
}
