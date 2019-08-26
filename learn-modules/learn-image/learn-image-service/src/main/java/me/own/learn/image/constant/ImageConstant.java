package me.own.learn.image.constant;

import me.own.commons.base.utils.enums.EnumName;

/**
 * @author yexudong
 * @date 2019/7/3 14:05
 */
public class ImageConstant {

    public enum ImageType implements EnumName {

        product_img(1, "productImg"),
        head_img(2, "headImg");

        ImageType(int code, String name) {
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
