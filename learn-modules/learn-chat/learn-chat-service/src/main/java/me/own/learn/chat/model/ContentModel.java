package me.own.learn.chat.model;

import java.util.Date;
import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/19 10:42
 */
public class ContentModel {

    //1.text 2.image 3.video 4.
    private int type;

    private String content;

    private Long from;

    private Long to;

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

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
