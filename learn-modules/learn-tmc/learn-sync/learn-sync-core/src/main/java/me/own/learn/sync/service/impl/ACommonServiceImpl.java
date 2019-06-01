package me.own.learn.sync.service.impl;

import me.own.learn.sync.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * @author:simonye
 * @date 0:04 2019/4/27
 **/
@Service("ACommonServiceImpl")
public class ACommonServiceImpl implements CommonService {
    @Override
    public String getOut() {
        return "a";
    }
}
