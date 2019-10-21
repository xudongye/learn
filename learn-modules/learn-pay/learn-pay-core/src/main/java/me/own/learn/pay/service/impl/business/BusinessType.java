package me.own.learn.pay.service.impl.business;

import me.own.commons.base.utils.enums.EnumName;

public enum BusinessType implements EnumName {

    Order(1, "Order"),
    Crowd(2, "Crowd"),
    Reward(3, "Reward");

    private int code;

    private String name;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    BusinessType(int code, String name){
        this.code = code;
        this.name = name;
    }
}
