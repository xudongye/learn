package me.own.learn.agent.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/7/24 14:02
 */
@Entity
@Table(name = "learn_agent_commission")
public class AgentCommission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
