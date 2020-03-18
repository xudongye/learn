package me.own.learn.chat.model;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:38
 */
public class MessageModel {

    private String text;

    private String imageUrl;

    private String videoUrl;
    //发送时间
    private Long timestamp;
    //发送者
    private Long sender_cid;
    //接收者
    private Long receiver_cid;
    //已读
    private Boolean markRead;
    //所属聊天室
    private String chatRoomNo;

    public Long getSender_cid() {
        return sender_cid;
    }

    public void setSender_cid(Long sender_cid) {
        this.sender_cid = sender_cid;
    }

    public Long getReceiver_cid() {
        return receiver_cid;
    }

    public void setReceiver_cid(Long receiver_cid) {
        this.receiver_cid = receiver_cid;
    }

    public Boolean getMarkRead() {
        return markRead;
    }

    public void setMarkRead(Boolean markRead) {
        this.markRead = markRead;
    }

    public String getChatRoomNo() {
        return chatRoomNo;
    }

    public void setChatRoomNo(String chatRoomNo) {
        this.chatRoomNo = chatRoomNo;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
