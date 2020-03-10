package me.own.learn.mall.market.service;

/**
 * @author: yexudong
 * @Date: 2020/3/10 15:30
 */
public class HomeAdvertiseQueryCondition {
    private String name;
    private Integer type;
    private Long startTime;
    private Long endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
