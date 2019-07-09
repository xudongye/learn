package me.own.learn.agent.constant;

import me.own.commons.base.utils.enums.EnumName;

/**
 * @author yexudong
 * @date 2019/7/5 9:47
 */
public class AgentConstant {

    public enum AgentRequestStatus implements EnumName {

        pending(1, "申请中"),
        approval(2, "批准"),
        rejected(3, "拒绝");

        AgentRequestStatus(int code, String name) {
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

    public enum AgentType implements EnumName {

        individual(1, "个人"),
        enterprise(2, "企业");

        AgentType(int code, String name) {
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
