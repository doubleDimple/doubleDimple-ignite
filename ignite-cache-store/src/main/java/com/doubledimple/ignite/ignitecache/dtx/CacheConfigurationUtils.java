package com.doubledimple.ignite.ignitecache.dtx;

import com.doubledimple.ignite.ignitecache.store.mode1.MySqlDB1Store;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;

import javax.cache.configuration.FactoryBuilder;

public class CacheConfigurationUtils {

    private static final Boolean READ_THROUGH = true;
    private static final Boolean WRITE_THROUGH = true;
    private static final Boolean WRITE_BEHIND_ENABLED = true;


    public static <T> CacheConfiguration<String,T> getCacheConfiguration(Class<T> type,String cacheName,Class factoryClazz){
        CacheConfiguration<String, T> cacheConfig = new CacheConfiguration<>();
        cacheConfig.setName(cacheName);
        cacheConfig.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        cacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(factoryClazz));
        cacheConfig.setReadThrough(READ_THROUGH);
        cacheConfig.setWriteThrough(WRITE_THROUGH);
        cacheConfig.setWriteBehindEnabled(WRITE_BEHIND_ENABLED);
        return cacheConfig;
    }
}
