package me.own.learn.agent.service;

/**
 * @author yexudong
 * @date 2019/7/25 10:12
 */
public class AgentQueryCondition {
    private String name;
    private String businessLicense;
    private String telephone;
    private String email;
    private String address;
    private String createFrom;
    private String createTo;
    private Long parentId;
    private Long customerId;
    private Long adminId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
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

    public String getCreateFrom() {
        return createFrom;
    }

    public void setCreateFrom(String createFrom) {
        this.createFrom = createFrom;
    }

    public String getCreateTo() {
        return createTo;
    }

    public void setCreateTo(String createTo) {
        this.createTo = createTo;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
