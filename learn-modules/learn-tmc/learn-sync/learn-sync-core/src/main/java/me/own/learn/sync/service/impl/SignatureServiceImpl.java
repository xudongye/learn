package me.own.learn.sync.service.impl;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.sync.dao.SignatureDao;
import me.own.learn.sync.bo.SignatureResultBo;
import me.own.learn.sync.exception.SignatureFailureException;
import me.own.learn.sync.po.Signature;
import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.utils.SignatureUtils;
import me.own.learn.sync.vo.SignatureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yexudong
 * @date 2019/4/16 18:22
 */
@Service
public class SignatureServiceImpl implements SignatureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignatureServiceImpl.class);

    @Autowired
    private SignatureDao signatureDao;

    @Override
    public SignatureVo create(String requestType) {
        SignatureResultBo signatureResultBo = SignatureUtils.requestSignature(requestType);
        if (signatureResultBo == null) {
            throw new SignatureFailureException();
        }
        Signature signature = new Signature(requestType, signatureResultBo.getSignature(), signatureResultBo.getTimestamp());
        signatureDao.create(signature);
        LOGGER.info("create new signature {} type {}", signature.getSignature(), signature.getRequestType());
        return Mapper.Default().map(signature, SignatureVo.class);
    }

    @Override
    public SignatureVo getByRequestType(String requestType) {
        Signature signature = signatureDao.get(requestType);
        if (signature == null) {
            SignatureVo signatureVo = this.create(requestType);
            LOGGER.info("create signature {} type {} when expire.", signatureVo.getSignature(), signatureVo.getRequestType());
            return signatureVo;
        }
        return Mapper.Default().map(signature, SignatureVo.class);
    }

}
