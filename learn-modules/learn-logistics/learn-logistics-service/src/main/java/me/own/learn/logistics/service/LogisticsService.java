package me.own.learn.logistics.service;

import me.own.learn.logistics.bo.LogisticsRequestBo;
import me.own.learn.logistics.vo.LogisticsCompanyVo;
import me.own.learn.logistics.vo.LogisticsVo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/9/2 13:10
 */
public interface LogisticsService {

    LogisticsVo get(LogisticsRequestBo logisticsRequestBo);

    List<LogisticsCompanyVo> getSupported();


}
