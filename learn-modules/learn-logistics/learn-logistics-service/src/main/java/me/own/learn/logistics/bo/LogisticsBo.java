package me.own.learn.logistics.bo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/9/5 14:14
 */
public class LogisticsBo {
    private String company;
    private String com;
    private String no;
    private String status;
    private String status_detail;
    private List<Detail> list;

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

    public List<Detail> getList() {
        return list;
    }

    public void setList(List<Detail> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "LogisticsBo{" +
                "company='" + company + '\'' +
                ", com='" + com + '\'' +
                ", no='" + no + '\'' +
                ", status='" + status + '\'' +
                ", status_detail='" + status_detail + '\'' +
                ", list=" + list +
                '}';
    }

    static class Detail {
        private String datetime;
        private String remark;
        private String zone;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        @Override
        public String toString() {
            return "Detail{" +
                    "datetime='" + datetime + '\'' +
                    ", remark='" + remark + '\'' +
                    ", zone='" + zone + '\'' +
                    '}';
        }
    }
}


