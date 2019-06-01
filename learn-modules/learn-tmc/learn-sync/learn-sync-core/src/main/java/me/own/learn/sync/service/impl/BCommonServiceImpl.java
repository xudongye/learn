package me.own.learn.sync.service.impl;

import me.own.learn.sync.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * @author:simonye
 * @date 0:05 2019/4/27
 **/
@Service("BCommonServiceImpl")
public class BCommonServiceImpl implements CommonService {
    @Override
    public String getOut() {
        return "b";
    }
}
