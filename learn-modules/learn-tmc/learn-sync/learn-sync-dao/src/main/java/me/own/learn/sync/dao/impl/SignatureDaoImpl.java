package me.own.learn.sync.dao.impl;

import me.own.learn.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.sync.dao.SignatureDao;
import me.own.learn.sync.po.Signature;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/4/16 18:13
 */
@Repository
public class SignatureDaoImpl extends BaseDaoImpl<Signature> implements SignatureDao {
    @Override
    protected Class<Signature> getEntityClass() {
        return Signature.class;
    }
}
