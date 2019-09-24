package me.own.learn.configuration.service.j2cache;

import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    @Override
    public CacheManager cacheManager() {
        // 引入配置
        J2CacheConfig config = null;
        try {
            config = J2CacheConfig.initFromConfig("/j2cache.properties");
            // 生成 J2CacheBuilder
            J2CacheBuilder j2CacheBuilder = J2CacheBuilder.init(config);
            // 构建适配器
            J2CacheSpringCacheManageAdapter j2CacheSpringCacheManageAdapter = new J2CacheSpringCacheManageAdapter(j2CacheBuilder, true);
            LOGGER.info("j2cache int success!");
            return j2CacheSpringCacheManageAdapter;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("j2cache init failure!");
        }
    }
}
