package me.own.learn.configuration.service.strategy.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import me.own.learn.configuration.service.bean.LearnConfigurationBean;
import me.own.learn.configuration.service.strategy.YamlFileConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yexudong
 * @date 2019/4/17 16:26
 */
public class YamlFileConfigurationLoaderImpl implements YamlFileConfigurationLoader {
    @Override
    public LearnConfigurationBean load(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(
                inputStream,
                LearnConfigurationBean.class
        );
    }
}
