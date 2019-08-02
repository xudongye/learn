package me.own.learn.agent.vo;

import java.util.Date;

/**
 * @author yexudong
 * @date 2019/7/24 14:07
 */
public class AgentVo {
    private Long id;
    private String name;
    /**
     * 代理商类型。individual,enterprise
     */
    private Integer agentType;
    /**
     * 营业执照编号
     */
    private String businessLicense;
    private String description;
    private String telephone;
    private String email;
    private String address;
    /**
     * 是否是根节点，true表示是，则可以发展下线
     */
    private Boolean root;
    /**
     * 分配的二维码数量上限
     */
    private Integer assignedQR;
    private String qrUrl;
    private String qrContent;
    private Boolean enable;
    /**
     * 提成比例，0.3表示提成30%
     */
    private Double rate;
    private Double childrenRate;
    /**
     * 累计总提成
     */
    private double totalCommission;

    private double totalMoneyTransfer;
    /***
     * 设置分销商带入的会员是否能够加入分享会员
     */
    private Boolean memberJoinShareEnable;
    private Date createTime;
    private Date modifyTime;
    private Long parentId;
    private Long customerId;
    private Long adminId;

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

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

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getRoot() {
        return root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    public Integer getAssignedQR() {
        return assignedQR;
    }

    public void setAssignedQR(Integer assignedQR) {
        this.assignedQR = assignedQR;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public String getQrContent() {
        return qrContent;
    }

    public void setQrContent(String qrContent) {
        this.qrContent = qrContent;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getChildrenRate() {
        return childrenRate;
    }

    public void setChildrenRate(Double childrenRate) {
        this.childrenRate = childrenRate;
    }

    public double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(double totalCommission) {
        this.totalCommission = totalCommission;
    }

    public double getTotalMoneyTransfer() {
        return totalMoneyTransfer;
    }

    public void setTotalMoneyTransfer(double totalMoneyTransfer) {
        this.totalMoneyTransfer = totalMoneyTransfer;
    }

    public Boolean getMemberJoinShareEnable() {
        return memberJoinShareEnable;
    }

    public void setMemberJoinShareEnable(Boolean memberJoinShareEnable) {
        this.memberJoinShareEnable = memberJoinShareEnable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
