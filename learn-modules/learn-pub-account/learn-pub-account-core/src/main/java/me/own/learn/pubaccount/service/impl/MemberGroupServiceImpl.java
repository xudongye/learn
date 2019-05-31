package me.own.learn.pubaccount.service.impl;

import me.own.commons.wechat.pubaccount.user.GroupService;
import me.own.commons.wechat.pubaccount.user.impl.GroupServiceImpl;
import me.own.learn.pubaccount.service.MemberGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberGroupServiceImpl implements MemberGroupService {
    private GroupService groupService = new GroupServiceImpl();

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TenantService tenantService;

    @Override
    public void updateMemberGroup(String appId, String openId, String toGroupId) {
        groupService.updateMemberGroup(appId, openId, toGroupId);
    }

    @Override
    public void updateMemberGroup(long tenantId, String openId, String toGroupId) {
        String pubAccountAppId = tenantService.get(tenantId).getPubAccountAppId();
        updateMemberGroup(pubAccountAppId, openId, toGroupId);
    }

    @Override
    public void updateMemberGroup(long customerId, String toGroupId) {
        CustomerDto customer = customerService.get(customerId);
        Long tenantId = customer.getTenantId();
        String pubAccountAppId = tenantService.get(tenantId).getPubAccountAppId();
        updateMemberGroup(pubAccountAppId, customer.getOpenid(), toGroupId);
    }
}
