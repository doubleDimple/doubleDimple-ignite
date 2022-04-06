package com.doubledimple.ignite.ignitecache.utils;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.configuration.FactoryBuilder;

public class CacheConfigurationUtils {

    private static final Boolean READ_THROUGH = true;
    private static final Boolean WRITE_THROUGH = true;
    private static final Boolean WRITE_BEHIND_ENABLED = true;


    @SuppressWarnings("unchecked")
    public static <T> CacheConfiguration<String,T> getCacheConfiguration(Class<T> type,String cacheName,Class storeFactory){
        CacheConfiguration<String, T> cacheConfig = new CacheConfiguration<>();
        cacheConfig.setName(cacheName);
        cacheConfig.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        cacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(storeFactory));
        cacheConfig.setReadThrough(READ_THROUGH);
        cacheConfig.setWriteThrough(WRITE_THROUGH);
        cacheConfig.setWriteBehindEnabled(WRITE_BEHIND_ENABLED);
        return cacheConfig;
    }
}
