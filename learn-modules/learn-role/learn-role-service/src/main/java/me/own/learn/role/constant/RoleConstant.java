package me.own.learn.role.constant;

import me.own.commons.base.utils.enums.EnumName;

/**
 * @author yexudong
 * @date 2019/5/28 15:28
 */
public class RoleConstant {

    public enum RoleLevel implements EnumName {
        superAdmin(1, "超级管理员"),//超级管理员
        generalAdmin(2, "自定义管理员"),//自定义管理员
        ;

        RoleLevel(int code, String name) {
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
