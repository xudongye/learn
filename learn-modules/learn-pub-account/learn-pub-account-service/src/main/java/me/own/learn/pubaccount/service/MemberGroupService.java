package me.own.learn.pubaccount.service;

public interface MemberGroupService {
    void updateMemberGroup(String appId, String openId, String toGroupId);

    void updateMemberGroup(long pubAccountId, String openId, String toGroupId);

    void updateMemberGroup(long customerId, String toGroupId);
}
