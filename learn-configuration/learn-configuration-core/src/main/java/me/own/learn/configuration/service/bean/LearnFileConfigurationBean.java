package me.own.learn.configuration.service.bean;

import me.own.learn.configuration.template.LearnFileConfigurationTemplate;

/**
 * @author yexudong
 * @date 2019/6/14 13:49
 */
public class LearnFileConfigurationBean implements LearnFileConfigurationTemplate {

    private String root;

    private String urlPrefix;

    private String qrCodePath;

    private String qrCodeLogoPath;

    @Override
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    @Override
    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    @Override
    public String getQrCodeLogoPath() {
        return qrCodeLogoPath;
    }

    public void setQrCodeLogoPath(String qrCodeLogoPath) {
        this.qrCodeLogoPath = qrCodeLogoPath;
    }
}
