package me.own.learn.sync.service;

import me.own.learn.sync.vo.SignatureVo;

/**
 * @author yexudong
 * @date 2019/4/16 18:18
 */
public interface SignatureService {

    /**
     * 产生签名
     *
     * @param requestType
     * @return
     */
    SignatureVo create(String requestType);


    /**
     * 根据请求类型获取签名
     *
     * @param requestType
     * @return
     */
    SignatureVo getByRequestType(String requestType);

}
