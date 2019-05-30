package me.own.learn.customer.service;

/**
 * @author yexudong
 * @date 2019/5/30 16:45
 */
public class CustomerQueryCondition {
    private String nickName;
    private String cellphone;
    private String email;
    private String openid;
    private String sex;
    private String city;
    private String country;
    private String province;
    private Long subscribeTimeF;
    private Long subscribeTimeT;
    private String source;
    private Long birthdayF;
    private Long birthdayT;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getSubscribeTimeF() {
        return subscribeTimeF;
    }

    public void setSubscribeTimeF(Long subscribeTimeF) {
        this.subscribeTimeF = subscribeTimeF;
    }

    public Long getSubscribeTimeT() {
        return subscribeTimeT;
    }

    public void setSubscribeTimeT(Long subscribeTimeT) {
        this.subscribeTimeT = subscribeTimeT;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getBirthdayF() {
        return birthdayF;
    }

    public void setBirthdayF(Long birthdayF) {
        this.birthdayF = birthdayF;
    }

    public Long getBirthdayT() {
        return birthdayT;
    }

    public void setBirthdayT(Long birthdayT) {
        this.birthdayT = birthdayT;
    }
}
