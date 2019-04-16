package me.own.learn.sync.service;

import me.own.learn.sync.dot.SignatureDto;
import me.own.learn.sync.vo.SignatureVo;

/**
 * @author yexudong
 * @date 2019/4/16 18:18
 */
public interface SignatureService {

    /**
     * 产生签名
     *
     * @param signatureDto
     * @return
     */
    SignatureVo create(SignatureDto signatureDto);

    /**
     * 刷新签名
     *
     * @param signatureDto
     * @return
     */
    SignatureVo refresh(SignatureDto signatureDto);


    /**
     * 根据请求类型获取签名
     *
     * @param requestType
     * @return
     */
    SignatureVo getByRequestType(String requestType);
}
