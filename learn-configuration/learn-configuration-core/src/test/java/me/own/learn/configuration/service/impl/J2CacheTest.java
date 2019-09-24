package me.own.learn.configuration.service.impl;

import me.own.learn.test.base.BaseTestConfiguration;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import org.junit.Test;

public class J2CacheTest extends BaseTestConfiguration {


    @Test
    public void testJ2Cache() {
        CacheChannel channel = J2Cache.getChannel();
        channel.set("default", "1", "j2cache");
        System.out.println(channel.get("default", "1"));
        channel.evict("default", "1");
        System.out.println(channel.get("default", "1"));

        channel.close();
    }
}
