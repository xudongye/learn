package me.own.learn.chat.po;

import me.own.commons.base.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: yexudong
 * @Date: 2020/3/20 11:09
 */
@Entity
@Table(name = "chat_room_user_relation")
public class ChatRoomUserRelation extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long roomId;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
