package me.own.learn.chat.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: yexudong
 * @Date: 2020/3/20 11:26
 */
@Entity
public class MsgIdentifier implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column
    private String date;

    @Column
    private int dailySequence;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDailySequence() {
        return dailySequence;
    }

    public void setDailySequence(int dailySequence) {
        this.dailySequence = dailySequence;
    }
}
