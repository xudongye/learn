package me.own.learn.chat.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:23
 */
@Entity
@Table(name = "chat_msg")
public class ChatMessage implements Serializable {

    @Id
    @GeneratedValue(generator = "MessageKeyGenerator")
    @GenericGenerator(name = "MessageKeyGenerator", strategy = "me.own.learn.chat.po.MessageKeyGenerator")
    @Column(length = 150)
    private Long id;
    @Lob
    private String text;

    private String imageUrl;

    private String videoUrl;
    //发送时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    //已读
    private Boolean markRead;

    private Long roomId;

    private Boolean deleted;

    private Long from;

    private Long to;


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

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
