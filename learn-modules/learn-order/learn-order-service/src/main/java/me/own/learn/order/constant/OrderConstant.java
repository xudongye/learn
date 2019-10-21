package me.own.learn.order.constant;

import me.own.commons.base.utils.enums.EnumName;

public class OrderConstant {


    /**
     * Payment type support
     *
     * @author Yankee
     */
    public enum PaymentType implements EnumName {
        /**
         * Paid totally by coupon
         */
        Coupon(1, "优惠券"),

        /**
         * Paid with alipay
         */
        Alipay(2, "支付宝"),

        /**
         * Paid with wxpay
         */
        Wxpay(3, "微信支付"),

        /**
         * Paid with customer's points
         */
        Point(4, "积分支付");

        private int code;

        private String name;

        private PaymentType(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }

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
