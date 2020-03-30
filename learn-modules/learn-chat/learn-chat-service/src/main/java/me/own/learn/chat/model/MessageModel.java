package me.own.learn.chat.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/1/7 11:38
 */
public class MessageModel {

    private String content;
    private Map<String, Long> userIds;
    private Date sendTime = new Date();

    private static ObjectMapper mapper = new ObjectMapper();

    public void setContent(String name, String msg) {
        this.content = name + " " + DateFormat.getDateTimeInstance().format(sendTime) + ":<br/> " + msg;
    }

    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    public MessageModel(String content, Map<String, Long> userIds) {
        this.content = content;
        this.userIds = userIds;
    }

    public MessageModel() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Map<String, Long> userIds) {
        this.userIds = userIds;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
