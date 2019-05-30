package me.own.learn.agent.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yexudong
 * @date 2019/5/30 14:24
 */
@Entity(name = "learn_agent_request")
public class AgentRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private String address;
    private String remark;
    private String businessLicense;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date handledTime;
    /**
     * 申请状态。pending，approval,rejected
     */
    private Integer status;
    /**
     * 代理商类型。individual,enterprise
     */
    private Integer agentType;
    
    private Long customerId;
}
