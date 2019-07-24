package me.own.learn.event.service.message.agent;

import me.own.learn.event.service.MessageCarriable;

/**
 * @author yexudong
 * @date 2019/7/24 11:34
 */
public class AgentMessage implements MessageCarriable {
    private String name;
    private String headImg;
    private String cellphone;
    private String email;

    public AgentMessage() {
    }

    public AgentMessage(String name, String headImg, String cellphone, String email) {
        this.name = name;
        this.headImg = headImg;
        this.cellphone = cellphone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
