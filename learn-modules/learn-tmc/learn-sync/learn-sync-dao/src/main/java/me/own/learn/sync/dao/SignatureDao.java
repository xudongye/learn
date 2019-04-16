package me.own.learn.sync.dao;


import me.own.learn.sync.po.Signature;

/**
 * @author yexudong
 * @date 2019/4/16 18:13
 */
public interface SignatureDao {

    Signature get(String value);

    void create(Signature signature);

    void delete(String value);

    void update(Signature signature);
}
