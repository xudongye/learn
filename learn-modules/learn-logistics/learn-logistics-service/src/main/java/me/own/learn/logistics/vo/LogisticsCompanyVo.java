package me.own.learn.logistics.vo;

/**
 * @author yexudong
 * @date 2019/9/2 15:10
 */
public class LogisticsCompanyVo {
    private Long id;
    private String com;
    private String no;
    private Boolean supported;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }
}
