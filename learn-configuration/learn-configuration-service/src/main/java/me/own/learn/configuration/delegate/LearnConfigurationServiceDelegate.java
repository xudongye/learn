package me.own.learn.configuration.delegate;

import me.own.learn.configuration.service.LearnConfigurationService;
import me.own.learn.configuration.template.LearnConfigurationTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yexudong
 * @date 2019/4/17 16:29
 */
public class LearnConfigurationServiceDelegate {

    private static final String lock = new String("lock");

    @Autowired
    private LearnConfigurationService learnConfigurationService;

    private static LearnConfigurationServiceDelegate delegate;

    private LearnConfigurationServiceDelegate() {

    }

    public static LearnConfigurationServiceDelegate getInstance() {
        if (delegate == null){
            synchronized (lock){
                if (delegate == null){
                    delegate = new LearnConfigurationServiceDelegate();
                }
            }
        }
        return delegate;
    }

    public LearnConfigurationTemplate getConfiguration(){
        return learnConfigurationService.getConfiguration();
    }

    public void loadFileConfig() {
        learnConfigurationService.loadFileConfig();
    }
}
