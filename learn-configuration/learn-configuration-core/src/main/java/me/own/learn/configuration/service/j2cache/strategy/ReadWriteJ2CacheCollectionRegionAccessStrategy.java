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


import me.own.learn.configuration.service.j2cache.regions.J2CacheCollectionRegion;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cfg.Settings;

public class ReadWriteJ2CacheCollectionRegionAccessStrategy extends AbstractReadWriteJ2CacheAccessStrategy<J2CacheCollectionRegion> implements CollectionRegionAccessStrategy {

    public ReadWriteJ2CacheCollectionRegionAccessStrategy(J2CacheCollectionRegion region, Settings settings) {
        super(region, settings);
    }

    @Override
    public CollectionRegion getRegion() {
        return region();
    }

}