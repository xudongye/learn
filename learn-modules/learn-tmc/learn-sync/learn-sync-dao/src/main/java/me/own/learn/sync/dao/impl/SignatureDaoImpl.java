package me.own.learn.sync.dao.impl;


import me.own.learn.commons.base.dao.impl.BaseRedisJsonHashDaoImpl;
import me.own.learn.sync.dao.SignatureDao;
import me.own.learn.sync.po.Signature;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author yexudong
 * @date 2019/4/16 18:13
 */
@Repository
public class SignatureDaoImpl extends BaseRedisJsonHashDaoImpl<String, Signature> implements SignatureDao {

    private static final String SIGNATURE_HISTORY_KEY = "learn:signature:value:{{value}}";

    @Override
    protected Class<Signature> getEntityClass() {
        return Signature.class;
    }

    @Override
    protected String convertKey(String key) {
        return concatKeyByTokenValue(key);
    }

    @Override
    public Signature get(String value) {
        return super.getEntity(value);
    }

    @Override
    public void create(Signature signature) {
        super.setEntity(signature.getRequestType(), signature);
        super.expire(signature.getRequestType(), 5, TimeUnit.MINUTES);
    }


    @Override
    public void delete(String value) {
        super.delete(value);
    }

    @Override
    public void update(Signature signature) {
        super.setEntity(signature.getRequestType(), signature);
    }

    private static String concatKeyByTokenValue(String value) {
        return SIGNATURE_HISTORY_KEY.replace("{{value}}", value);
    }
}
