package me.own.learn.chat.vo;

import java.util.Date;

/**
 * @author: yexudong
 * @Date: 2020/3/19 9:27
 */
public class ChatRoomVo {

    private Long id;

    private Long customerId;

    private String customername;

    private Long userId;

    private String username;
    //用户删除状态
    private Boolean cDeleted;
    //管理员删除状态
    private Boolean uDeleted;

    private Boolean deleted;

    private Date createTime;

    private Date modifyTime;

    private Long msgCount;

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getcDeleted() {
        return cDeleted;
    }

    public void setcDeleted(Boolean cDeleted) {
        this.cDeleted = cDeleted;
    }

    public Boolean getuDeleted() {
        return uDeleted;
    }

    public void setuDeleted(Boolean uDeleted) {
        this.uDeleted = uDeleted;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(Long msgCount) {
        this.msgCount = msgCount;
    }
}
