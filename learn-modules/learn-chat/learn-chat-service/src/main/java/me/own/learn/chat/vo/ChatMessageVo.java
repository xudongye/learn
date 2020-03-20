package me.own.learn.chat.vo;

import java.util.Date;

/**
 * @author: yexudong
 * @Date: 2020/3/18 16:25
 */
public class ChatMessageVo {
    private Long id;

    private String text;

    private String imageUrl;

    private String videoUrl;
    //发送时间
    private Date sendTime;

    private Long userId;

    private Long customerId;
    //已读
    private Boolean markRead;

    private String roomId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getMarkRead() {
        return markRead;
    }

    public void setMarkRead(Boolean markRead) {
        this.markRead = markRead;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
