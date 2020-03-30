package me.own.learn.chat.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: yexudong
 * @Date: 2020/3/24 10:06
 */
@Entity
@Table(name = "chat_msg_user_relation")
public class ChatMsgUserRelation implements Serializable {

    @Id
    @GeneratedValue(generator = "MsgUserRelationKeyGenerator")
    @GenericGenerator(name = "MsgUserRelationKeyGenerator", strategy = "me.own.learn.chat.po.MsgUserRelationKeyGenerator")
    @Column(length = 150)
    private String muId;

    private String msgId;

    private Long userId;

    private Boolean deleted;

    private Boolean readMark;

    public String getMuId() {
        return muId;
    }

    public void setMuId(String muId) {
        this.muId = muId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getReadMark() {
        return readMark;
    }

    public void setReadMark(Boolean readMark) {
        this.readMark = readMark;
    }
}
