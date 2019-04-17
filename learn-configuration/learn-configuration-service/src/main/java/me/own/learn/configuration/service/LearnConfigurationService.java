package me.own.learn.configuration.service;

import me.own.learn.configuration.template.LearnConfigurationTemplate;

/**
 * @author yexudong
 * @date 2019/4/17 16:20
 */
public interface LearnConfigurationService {

    LearnConfigurationTemplate getConfiguration();

    void loadFileConfig();
}
