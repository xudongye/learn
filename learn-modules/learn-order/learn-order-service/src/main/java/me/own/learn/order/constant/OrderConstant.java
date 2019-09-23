package me.own.learn.order.constant;

import me.own.commons.base.utils.enums.EnumName;

public class OrderConstant {

    public enum OrderStatus implements EnumName {

        unpaid(1, "待支付"),
        canceled(2, "已取消"),
        paid(3, "已支付"),
        sentOut(4, "已发货");

        OrderStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }

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
    }
}
