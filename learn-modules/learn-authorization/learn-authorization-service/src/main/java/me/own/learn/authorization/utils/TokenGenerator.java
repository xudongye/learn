package me.own.learn.authorization.utils;

import me.own.learn.commons.base.utils.encode.EncodeUtils;

import java.util.Random;

/**
 * @author yexudong
 * @date 2018/4/23 17:37
 */
public class TokenGenerator {
    private TokenGenerator(){}
    private static  TokenGenerator instance = new TokenGenerator();
    public static  TokenGenerator getInstance(){
        return instance;
    }
    public synchronized String generateTokenCode(String args){
        String value = args + System.currentTimeMillis() + new Random().nextLong()+"";
        return EncodeUtils.encodeByMD5Base64(value);
    }
}
