package me.own.learn.sync.service.impl;

import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:simonye
 * @date 21:37 2019/4/16
 **/
@Service
public class SyncServiceImpl implements SyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncServiceImpl.class);

    @Autowired
    private SignatureService signatureService;

    @Override
    public void syncCountries() {

    }

    @Override
    public void syncCities() {

    }

    @Override
    public void syncHotels() {

    }


}
