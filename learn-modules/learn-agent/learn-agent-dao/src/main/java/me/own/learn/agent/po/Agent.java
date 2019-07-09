package me.own.learn.agent.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yexudong
 * @date 2019/5/30 14:04
 */
@Entity
@Table(name = "learn_agent")
public class Agent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortName;
    /**
     * 营业执照编号
     */
    private String businessLicense;
    private String description;
    private String contact;
    private String telephone;
    private String email;
    private String address;
    /**
     * 是否是根节点，true表示是，则可以发展下线
     */
    private Boolean isRoot;
    /**
     * 层级，顶级为0级，以下为1、2级
     */
    private Integer layer;
    /**
     * 最大发展层级
     */
    private Integer maxGeneration;
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
    private String code;
    /**
     * 家族链地址，用来优化递归查询
     */
    private String ancestorChain;
    /**
     * 累计总提成
     */
    private double totalCommission;

    private double totalMoneyTransfer;
    /***
     * 设置分销商带入的会员是否能够加入分享会员
     */
    private Boolean memberJoinShareEnable;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        return isRoot;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getMaxGeneration() {
        return maxGeneration;
    }

    public void setMaxGeneration(Integer maxGeneration) {
        this.maxGeneration = maxGeneration;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAncestorChain() {
        return ancestorChain;
    }

    public void setAncestorChain(String ancestorChain) {
        this.ancestorChain = ancestorChain;
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
}
