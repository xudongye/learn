package me.own.learn.logistics.vo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/9/2 13:14
 */
public class LogisticsResultVo {
    private String company;
    private String com;
    private String no;
    private String status;
    private String status_detail;
    private List<LogisticsListVo> list;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_detail() {
        return status_detail;
    }

    public void setStatus_detail(String status_detail) {
        this.status_detail = status_detail;
    }

    public List<LogisticsListVo> getList() {
        return list;
    }

    public void setList(List<LogisticsListVo> list) {
        this.list = list;
    }
}
