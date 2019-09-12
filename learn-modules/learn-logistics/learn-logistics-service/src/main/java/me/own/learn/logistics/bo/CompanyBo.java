package me.own.learn.logistics.bo;

/**
 * @author yexudong
 * @date 2019/9/4 17:54
 */
public class CompanyBo {
    private String com;
    private String no;

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

    @Override
    public String toString() {
        return "CompanyBo{" +
                "com='" + com + '\'' +
                ", no='" + no + '\'' +
                '}';
    }
}
