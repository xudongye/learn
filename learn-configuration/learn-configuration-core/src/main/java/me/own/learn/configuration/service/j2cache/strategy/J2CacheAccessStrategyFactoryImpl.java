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
package me.own.learn.configuration.service.j2cache.strategy;



import me.own.learn.configuration.service.j2cache.log.J2CacheMessageLogger;
import me.own.learn.configuration.service.j2cache.regions.J2CacheCollectionRegion;
import me.own.learn.configuration.service.j2cache.regions.J2CacheEntityRegion;
import me.own.learn.configuration.service.j2cache.regions.J2CacheNaturalIdRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.jboss.logging.Logger;

public class J2CacheAccessStrategyFactoryImpl implements J2CacheAccessStrategyFactory {

    private static final J2CacheMessageLogger LOG = Logger.getMessageLogger(J2CacheMessageLogger.class, J2CacheAccessStrategyFactoryImpl.class.getName());

    public EntityRegionAccessStrategy createEntityRegionAccessStrategy(J2CacheEntityRegion entityRegion, AccessType accessType) {
        switch (accessType) {
            case READ_ONLY:
                if (entityRegion.getCacheDataDescription().isMutable()) {
                    LOG.readOnlyCacheConfiguredForMutableEntity(entityRegion.getName());
                }
                return new ReadOnlyJ2CacheEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteJ2CacheEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteJ2CacheEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalJ2CacheEntityRegionAccessStrategy(entityRegion, entityRegion.getJ2Cache(), entityRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");

        }

    }

    public CollectionRegionAccessStrategy createCollectionRegionAccessStrategy(J2CacheCollectionRegion collectionRegion, AccessType accessType) {
        switch (accessType) {
            case READ_ONLY:
                if (collectionRegion.getCacheDataDescription().isMutable()) {
                    LOG.readOnlyCacheConfiguredForMutableEntity(collectionRegion.getName());
                }
                return new ReadOnlyJ2CacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteJ2CacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteJ2CacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalJ2CacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getJ2Cache(), collectionRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }

    @Override
    public NaturalIdRegionAccessStrategy createNaturalIdRegionAccessStrategy(J2CacheNaturalIdRegion naturalIdRegion, AccessType accessType) {
        switch (accessType) {
            case READ_ONLY:
                if (naturalIdRegion.getCacheDataDescription().isMutable()) {
                    LOG.readOnlyCacheConfiguredForMutableEntity(naturalIdRegion.getName());
                }
                return new ReadOnlyJ2CacheNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteJ2CacheNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteJ2CacheNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalJ2CacheNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getJ2Cache(), naturalIdRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }

}
