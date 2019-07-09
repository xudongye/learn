package me.own.learn.agent.service;

/**
 * @author yexudong
 * @date 2019/7/5 10:15
 */
public class AgentRequestQueryCondition {
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private String address;
    private String remark;
    private String businessLicense;
    private Long createTimeFrom;
    private Long createTimeTo;
    private Long handledTimeFrom;
    private Long handledTimeTo;
    /**
     * 申请状态。pending，approval,rejected
     */
    private Integer status;
    /**
     * 代理商类型。individual,enterprise
     */
    private Integer agentType;

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

    public Long getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(Long createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public Long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(Long createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public Long getHandledTimeFrom() {
        return handledTimeFrom;
    }

    public void setHandledTimeFrom(Long handledTimeFrom) {
        this.handledTimeFrom = handledTimeFrom;
    }

    public Long getHandledTimeTo() {
        return handledTimeTo;
    }

    public void setHandledTimeTo(Long handledTimeTo) {
        this.handledTimeTo = handledTimeTo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
