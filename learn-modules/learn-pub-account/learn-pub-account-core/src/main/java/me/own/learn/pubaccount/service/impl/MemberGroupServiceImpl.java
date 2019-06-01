package me.own.learn.pubaccount.service.impl;

import me.own.commons.wechat.pubaccount.user.GroupService;
import me.own.commons.wechat.pubaccount.user.impl.GroupServiceImpl;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import me.own.learn.pubaccount.service.MemberGroupService;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberGroupServiceImpl implements MemberGroupService {
    private GroupService groupService = new GroupServiceImpl();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PubConfigurationService pubConfigurationService;


    @Override
    public void updateMemberGroup(String appId, String openId, String toGroupId) {
        groupService.updateMemberGroup(appId, openId, toGroupId);
    }

    @Override
    public void updateMemberGroup(long pubAccountId, String openId, String toGroupId) {
        String pubAccountAppId = pubConfigurationService.getById(pubAccountId).getPubAccountAppId();
        updateMemberGroup(pubAccountAppId, openId, toGroupId);
    }

    @Override
    public void updateMemberGroup(long customerId, String toGroupId) {
        CustomerVo customer = customerService.getById(customerId);
        Long pubAccountId = customer.getPubAccountId();
        String pubAccountAppId = pubConfigurationService.getById(pubAccountId).getPubAccountAppId();
        updateMemberGroup(pubAccountAppId, customer.getOpenid(), toGroupId);
    }
}
