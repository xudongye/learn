package me.own.learn.agent.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yexudong
 * @date 2019/5/30 14:04
 */
@Entity(name = "learn_agent")
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
}
