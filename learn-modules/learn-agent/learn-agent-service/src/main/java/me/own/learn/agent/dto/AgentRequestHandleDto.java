package me.own.learn.agent.dto;

import me.own.learn.agent.constant.AgentConstant;

/**
 * @author yexudong
 * @date 2019/7/5 10:09
 */
public class AgentRequestHandleDto {
    private Long id;
    private String remark;
    private AgentConstant.AgentRequestStatus requestStatus;
    private Long handlerId;
    private Integer assignedQR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AgentConstant.AgentRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(AgentConstant.AgentRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public Integer getAssignedQR() {
        return assignedQR;
    }

    public void setAssignedQR(Integer assignedQR) {
        this.assignedQR = assignedQR;
    }
}
