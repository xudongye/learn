package me.own.learn.chat.dto;

/**
 * @author: yexudong
 * @Date: 2020/1/8 14:53
 */
public class ChatUserDto {
    private Long id;
    private String headImg;
    private String name;
    private String autoResponse;
    private Boolean online;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutoResponse() {
        return autoResponse;
    }

    public void setAutoResponse(String autoResponse) {
        this.autoResponse = autoResponse;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
