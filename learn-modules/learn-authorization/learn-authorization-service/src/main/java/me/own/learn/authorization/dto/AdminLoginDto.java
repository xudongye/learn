package me.own.learn.authorization.dto;

/**
 * @author yexudong
 * @date 2019/5/30 10:15
 */
public class AdminLoginDto {
    private String loginLabel;
    private String password;

    public String getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(String loginLabel) {
        this.loginLabel = loginLabel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
