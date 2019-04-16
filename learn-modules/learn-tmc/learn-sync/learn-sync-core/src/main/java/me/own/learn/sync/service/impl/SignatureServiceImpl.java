package me.own.learn.sync.service.impl;

import me.own.learn.commons.base.utils.mapper.Mapper;
import me.own.learn.sync.dao.SignatureDao;
import me.own.learn.sync.dot.SignatureDto;
import me.own.learn.sync.po.Signature;
import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.vo.SignatureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    @Transactional
    public SignatureVo create(SignatureDto signatureDto) {
        Signature signature = Mapper.Default().map(signatureDto, Signature.class);
        signature.setEnable(true);
        signature.setRefreshTime(new Date());
        signatureDao.create(signature);
        LOGGER.info("create new signature {}", signature.getId());
        return Mapper.Default().map(signature, SignatureVo.class);
    }

    @Override
    public SignatureVo refresh(SignatureDto signatureDto) {
        return null;
    }

    @Override
    public SignatureVo getByRequestType(String requestType) {
        return null;
    }
}
