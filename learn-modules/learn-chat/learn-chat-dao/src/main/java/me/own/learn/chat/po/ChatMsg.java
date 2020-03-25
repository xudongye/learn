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
public class ChatMsg implements Serializable {

    public enum ContentType {
        TEXT(0), VIDEO(1), IMAGE(2);
        private int contentType;

        private ContentType(int type){
            contentType = type;
        }

        public int getContentType(){
            return contentType;
        }
    }


    @Id
    @GeneratedValue(generator = "MsgKeyGenerator")
    @GenericGenerator(name = "MsgKeyGenerator", strategy = "me.own.learn.chat.po.MsgKeyGenerator")
    @Column(length = 150)
    private String msgId;
    @Lob
    private String content;

    private int type;
    //发送时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    //发送者
    private Long userId;


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
