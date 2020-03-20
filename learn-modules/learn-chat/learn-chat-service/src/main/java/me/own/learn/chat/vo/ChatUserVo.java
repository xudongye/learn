package me.own.learn.chat.vo;

/**
 * @author: yexudong
 * @Date: 2020/3/20 16:42
 */
public class ChatUserVo {

    private String name;

    private String headImg;

    private Boolean online;

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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
