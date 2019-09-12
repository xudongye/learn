package me.own.learn.logistics.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.logistics.bo.LogisticsRequestBo;
import me.own.learn.logistics.dao.LogisticsCompanyDao;
import me.own.learn.logistics.po.LogisticsCompany;
import me.own.learn.logistics.service.LogisticsService;
import me.own.learn.logistics.vo.LogisticsCompanyVo;
import me.own.learn.logistics.vo.LogisticsVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/9/2 13:23
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {

    @Autowired
    private LogisticsCompanyDao logisticsCompanyDao;


    @Override
    @Transactional(readOnly = true)
    public LogisticsVo get(LogisticsRequestBo logisticsRequestBo) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LogisticsCompanyVo> getSupported() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(LogisticsCompany.class);
        query.setSimpleCondition("supported", true + "", QueryConstants.SimpleQueryMode.Equal);
        List<LogisticsCompany> companies = logisticsCompanyDao.filter(query, null, null);
        if (CollectionUtils.isNotEmpty(companies)) {
            return Mapper.Default().mapArray(companies, LogisticsCompanyVo.class);
        }
        return null;
    }
}
