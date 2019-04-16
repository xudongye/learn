package me.own.learn.user.po;

import me.own.learn.commons.base.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/16 17:07
 */
@Entity(name = "learn_user")
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
