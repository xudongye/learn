package me.own.learn.chat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: yexudong
 * @Date: 2020/3/30 15:53
 */
public class Channel implements Serializable {
    private String senderName;
    private int type;
    private long sender;
    private long receiver;
    private boolean readMark;
    private Date sendTime;
    private String content;
    private long msgCount;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public boolean isReadMark() {
        return readMark;
    }

    public void setReadMark(boolean readMark) {
        this.readMark = readMark;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(long msgCount) {
        this.msgCount = msgCount;
    }
}
