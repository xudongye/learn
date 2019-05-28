package me.own.learn.role.constant;

import me.own.learn.commons.base.utils.enums.EnumName;

/**
 * @author yexudong
 * @date 2019/5/28 15:28
 */
public class RoleConstant {

    public enum RoleLevel implements EnumName {
        superAdmin(0, "superAdmin"),//超级管理员
        generalAdmin(1, "generalAdmin"),//普通管理员
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
