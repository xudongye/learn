/**
 * Copyright (c) 2015-2017.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.own.learn.configuration.service.j2cache;

import me.own.learn.configuration.service.j2cache.regions.*;
import me.own.learn.configuration.service.j2cache.strategy.J2CacheAccessStrategyFactory;
import me.own.learn.configuration.service.j2cache.strategy.J2CacheAccessStrategyFactoryImpl;
import me.own.learn.configuration.service.j2cache.strategy.NonstopAccessStrategyFactory;
import me.own.learn.configuration.service.j2cache.util.Timestamper;
import net.oschina.j2cache.CacheChannel;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.*;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cfg.Settings;

import java.util.Properties;

abstract class AbstractJ2CacheRegionFactory implements RegionFactory {

    protected Settings settings;

    protected CacheChannel channel;

    protected final J2CacheAccessStrategyFactory accessStrategyFactory = new NonstopAccessStrategyFactory(new J2CacheAccessStrategyFactoryImpl());

    @Override
    public boolean isMinimalPutsEnabledByDefault() {
        return true;
    }

    @Override
    public long nextTimestamp() {
        return Timestamper.next();
    }

    @Override
    public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        return new J2CacheEntityRegion(accessStrategyFactory, getCache(regionName), settings, metadata, properties);
    }

    @Override
    public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        return new J2CacheNaturalIdRegion(accessStrategyFactory, getCache(regionName), settings, metadata, properties);
    }

    @Override
    public CollectionRegion buildCollectionRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        return new J2CacheCollectionRegion(accessStrategyFactory, getCache(regionName), settings, metadata, properties);
    }

    @Override
    public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
        return new J2CacheQueryResultsRegion(accessStrategyFactory, getCache(regionName), properties);
    }

    @Override
    public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
        return new J2CacheTimestampsRegion(accessStrategyFactory, getCache(regionName), properties);
    }

    private CacheRegion getCache(String name) throws CacheException {
        return new J2CacheCacheRegion(channel,name);
    }

    public AccessType getDefaultAccessType() {
        return AccessType.READ_WRITE;
    }

    public void setChannel(CacheChannel channel) {
        this.channel = channel;
    }
}

