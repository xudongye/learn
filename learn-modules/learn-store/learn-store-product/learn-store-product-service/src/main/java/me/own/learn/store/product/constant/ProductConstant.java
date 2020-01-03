package me.own.learn.store.product.constant;

import me.own.commons.base.utils.enums.EnumName;

/**
 * @author yexudong
 * @date 2019/6/12 18:24
 */
public class ProductConstant {

    //常用计量单位
    public enum Unit implements EnumName {

        piece(1, "件"),
        kg(2, "千克"),
        L(3, "升");

        private int code;

        private String name;

        private Unit(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }

    public enum SoldStatus implements EnumName {

        putaway(1, "上架"),
        soldout(2, "下架/售罄");

        private int code;

        private String name;

        private SoldStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }

    public enum PriceSort implements EnumName {

        lToh(1, "从低到高"),
        hTol(2, "从高到低");

        private int code;

        private String name;

        private PriceSort(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
