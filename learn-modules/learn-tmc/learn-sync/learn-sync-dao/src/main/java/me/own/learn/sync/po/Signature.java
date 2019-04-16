package me.own.learn.sync.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yexudong
 * @date 2019/4/16 18:08
 */
@Entity(name = "sync_signature")
public class Signature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String requestType;
    @Column
    private String signature;
    @Column
    private Long timestamp;
    @Column
    private Boolean enable;
    @Temporal(TemporalType.TIMESTAMP)
    private Date refreshTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }
}
