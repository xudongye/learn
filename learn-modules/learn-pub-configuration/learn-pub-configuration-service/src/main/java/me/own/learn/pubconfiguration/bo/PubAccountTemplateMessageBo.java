package me.own.learn.pubconfiguration.bo;

/**
 * @author yexudong
 * @date 2019/5/31 11:27
 */
public class PubAccountTemplateMessageBo {
    private String template_id;
    private String url;
    private String topcolor;
    private TemplateDataBo data;

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public TemplateDataBo getData() {
        return data;
    }

    public void setData(TemplateDataBo data) {
        this.data = data;
    }

    public static class TemplateDataBo {
        private Data first;
        private Data remark;

        public Data getFirst() {
            return first;
        }

        public void setFirst(Data first) {
            this.first = first;
        }

        public Data getRemark() {
            return remark;
        }

        public void setRemark(Data remark) {
            this.remark = remark;
        }
    }

    public static class Data {
        private String value;
        private String color;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
