package me.own.learn.configuration.service.strategy;

import me.own.learn.configuration.service.bean.LearnConfigurationBean;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yexudong
 * @date 2019/4/17 16:24
 */
public interface YamlFileConfigurationLoader {

    LearnConfigurationBean load(InputStream inputStream) throws IOException;
}
