package me.own.learn.agent.dto;

import me.own.learn.agent.constant.AgentConstant;

/**
 * @author yexudong
 * @date 2019/7/5 9:53
 */
public class AgentRequestDto {
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private String address;
    private String remark;
    private String businessLicense;
    /**
     * 申请状态。pending，approval,rejected
     */
    private AgentConstant.AgentRequestStatus requestStatus;
    /**
     * 代理商类型。individual,enterprise
     */
    private AgentConstant.AgentType agentType;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public AgentConstant.AgentRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(AgentConstant.AgentRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public AgentConstant.AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentConstant.AgentType agentType) {
        this.agentType = agentType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
