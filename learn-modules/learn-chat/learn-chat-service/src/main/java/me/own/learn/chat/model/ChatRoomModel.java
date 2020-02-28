package me.own.learn.chat.model;

import me.own.commons.base.model.BaseEntity;

/**
 * @author: yexudong
 * @Date: 2020/1/9 15:40
 */
public class ChatRoomModel extends BaseEntity {
    private Long id;

    private String chatRoomName;

    private Long robotId;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public Long getRobotId() {
        return robotId;
    }

    public void setRobotId(Long robotId) {
        this.robotId = robotId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
