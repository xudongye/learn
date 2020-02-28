package me.own.learn.chat.dto;

/**
 * @author: yexudong
 * @Date: 2020/1/9 14:31
 */
public class ChatRoomDto {
    private Long id;

    private String chatRoomNo;

    private Long robotId;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatRoomNo() {
        return chatRoomNo;
    }

    public void setChatRoomNo(String chatRoomNo) {
        this.chatRoomNo = chatRoomNo;
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
