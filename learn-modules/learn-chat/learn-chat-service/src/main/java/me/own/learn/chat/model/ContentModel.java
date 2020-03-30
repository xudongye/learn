package me.own.learn.chat.model;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/19 10:42
 */
public class ContentModel {

    //1.text 2.image 3.video 4.channel
    private int type;

    private String content;

    private Long from;

    private List<Long> tos = new ArrayList<>();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public List<Long> getTos() {
        return tos;
    }

    public void setTos(List<Long> tos) {
        this.tos = tos;
    }
}
