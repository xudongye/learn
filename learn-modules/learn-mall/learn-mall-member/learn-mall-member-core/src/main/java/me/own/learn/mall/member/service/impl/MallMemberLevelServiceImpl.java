package me.own.learn.mall.member.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.member.dao.MallMemberLevelDao;
import me.own.learn.mall.member.po.MallMemberLevel;
import me.own.learn.mall.member.service.MallMemberLevelService;
import me.own.learn.mall.member.vo.MallMemberLevelVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/9 17:59
 */
@Service
public class MallMemberLevelServiceImpl implements MallMemberLevelService {

    @Autowired
    private MallMemberLevelDao mallMemberLevelDao;

    @Override
    @Transactional(readOnly = true)
    public List<MallMemberLevelVo> listByDefaultStatus(Integer defaultStatus) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(MallMemberLevel.class);
        if (defaultStatus != null) {
            query.setSimpleCondition("defaultStatus", defaultStatus + "", QueryConstants.SimpleQueryMode.Equal);
        }
        List<MallMemberLevel> memberLevels = mallMemberLevelDao.filter(query, null);
        if (CollectionUtils.isNotEmpty(memberLevels)) {
            return Mapper.Default().mapArray(memberLevels, MallMemberLevelVo.class);
        }
        return null;
    }
}
