package me.own.learn.agent.po;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    @Column(columnDefinition = "INT default 0")
    private Integer assignedQR;
    /**
     * 已使用的二维码数量
     */
    @Column(columnDefinition = "INT default 0")
    private Integer usedQR;

    private String qrUrl;
    private String qrContent;

    /**
     * 层级，顶级犁书为0级，以下为1、2级
     */
    @Column
    private Integer layer;

    /**
     * 最大发展层级
     */
    @Column
    private Integer maxGeneration;

    private Boolean enable;
    /**
     * 提成比例，0.3表示提成30%
     */
    private Double rate;
    private Double childrenRate;
    /**
     * 累计总提成
     */
    @Column(columnDefinition = "DOUBLE default 0.0")
    private double totalCommission;
    @Column(columnDefinition = "DOUBLE default 0.0")
    private double totalMoneyTransfer;
    /***
     * 设置分销商带入的会员是否能够加入分享会员
     */
    private Boolean memberJoinShareEnable;
    /**
     * 家族链地址，用来优化递归查询
     */
    private String ancestorChain;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    private Long parentId;
    private Long customerId;
    private Long adminId;

    public void useQR(int qrQuantity) {
        this.usedQR += qrQuantity;
    }

    /**
     * 增加累加提成
     *
     * @param commission
     */
    public void addCommission(double commission) {
        totalCommission = new BigDecimal(totalCommission + commission).setScale(2, RoundingMode.HALF_UP).doubleValue();
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

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
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

    public Integer getUsedQR() {
        return usedQR;
    }

    public void setUsedQR(Integer usedQR) {
        this.usedQR = usedQR;
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

    public String getAncestorChain() {
        return ancestorChain;
    }

    public void setAncestorChain(String ancestorChain) {
        this.ancestorChain = ancestorChain;
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
