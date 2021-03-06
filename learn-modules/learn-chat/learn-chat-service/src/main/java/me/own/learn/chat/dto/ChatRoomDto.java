package me.own.learn.chat.dto;

/**
 * @author: yexudong
 * @Date: 2020/1/9 14:31
 */
public class ChatRoomDto {
    private Long id;

    private Long customerId;

    private Long userId;
    //用户删除状态
    private Boolean cDeleted;
    //管理员删除状态
    private Boolean uDeleted;

    private Boolean deleted;

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
}
