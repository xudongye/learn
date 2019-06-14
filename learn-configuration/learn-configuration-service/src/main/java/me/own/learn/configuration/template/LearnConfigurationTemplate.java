package me.own.learn.configuration.template;

/**
 * @author yexudong
 * @date 2019/4/17 16:13
 */
public interface LearnConfigurationTemplate {

    String getDomain();

    LearnTmcConfigurationTemplate getTmc();

    LearnFileConfigurationTemplate getFile();
}
