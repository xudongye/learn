package me.own.learn.configuration.service.impl;

import me.own.learn.configuration.service.LearnConfigurationService;
import me.own.learn.configuration.service.bean.LearnConfigurationBean;
import me.own.learn.configuration.service.strategy.YamlFileConfigurationLoader;
import me.own.learn.configuration.service.strategy.impl.YamlFileConfigurationLoaderImpl;
import me.own.learn.configuration.template.LearnConfigurationTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yexudong
 * @date 2019/4/17 16:21
 */
@Service
public class LearnConfigurationServiceImpl implements LearnConfigurationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LearnConfigurationServiceImpl.class);

    private static LearnConfigurationTemplate configurationInstance;

    private static YamlFileConfigurationLoader yamlFileConfigurationLoader = new YamlFileConfigurationLoaderImpl();


    @Value("${learnConfigurationName:learn.config.yml}")
    private String cloudConfigurationName;

    @Override
    public LearnConfigurationTemplate getConfiguration() {
        if (configurationInstance == null) {
            loadFileConfig();
        }
        return configurationInstance;
    }

    @Override
    public void loadFileConfig() {
        InputStream is = getConfigInputStream();
        loadFileConfig(is);
    }

    private InputStream getConfigInputStream() {
        LOGGER.info("load configuration file: {}", cloudConfigurationName);
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(cloudConfigurationName);
        if (inputStream == null) {
            LOGGER.error("Fatal: cloud global config file: {} does not exist.", cloudConfigurationName);
        }
        return inputStream;
    }

    private void loadFileConfig(InputStream is){
        try {
            LearnConfigurationBean bean = yamlFileConfigurationLoader.load(is);
            configurationInstance = bean;
        } catch (IOException e) {
            LOGGER.error("Fatal: read configuration value error.", e);
        }
    }
}
