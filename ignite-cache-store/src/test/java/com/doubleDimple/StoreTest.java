package com.doubleDimple;


import com.doubledimple.ignite.ignitecache.IgniteCacheStoreApplication;
import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.store.MySqlDBStore;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.transactions.Transaction;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.cache.configuration.FactoryBuilder;

import static org.apache.ignite.transactions.TransactionConcurrency.PESSIMISTIC;
import static org.apache.ignite.transactions.TransactionIsolation.REPEATABLE_READ;

@SpringBootTest(classes = IgniteCacheStoreApplication.class)
public class StoreTest {

    private static final String PRODUCT_CACHE_NAME = StoreTest.class.getSimpleName() + "-product";
    private static Logger LOGGER = LoggerFactory.getLogger(com.doubledimple.ignite.ignitecache.store.StoreTest.class);

    @Test
    public void testStore() throws Exception {
        jdbcStoreExample();
    }

    private static void jdbcStoreExample() throws Exception {
        //构建一个动态缓存，它分布在所有运行的节点上。
        //也可以在xml配置文件中使用相同的配置
        IgniteConfiguration cfg = new IgniteConfiguration();

        cfg.setPeerClassLoadingEnabled(true);

        CacheConfiguration configuration = new CacheConfiguration();
        configuration.setName(PRODUCT_CACHE_NAME);
        configuration.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);

        configuration.setCacheStoreFactory(FactoryBuilder.factoryOf(MySqlDBStore.class));
        configuration.setReadThrough(true);
        configuration.setWriteThrough(true);

        configuration.setWriteBehindEnabled(true);

        log("Start. PersistenceStore example.");
        cfg.setCacheConfiguration(configuration);

        try(Ignite ignite = Ignition.start(cfg)) {
            int count = 10;
            try(IgniteCache<String, Product> igniteCache = ignite.getOrCreateCache(configuration)) {

                try (Transaction tx =  ignite.transactions().txStart(PESSIMISTIC,REPEATABLE_READ)){
                    for(int i = 1;i <= count;i++)
                    {
                        igniteCache.put("_"+i,new Product(Long.valueOf(i), "name-" + i));
                    }
                    tx.commit();

                    for (int i = 1;i < count;i+=2)
                    {
                        igniteCache.clear("_"+i);
                        log("Clear every odd key: " + i);
                    }

                    for (long i = 1;i <= count;i++)
                    {
                        log("Local peek at [key=_" + i + ", val=" + igniteCache.localPeek("_" + i) + ']');
                    }

                    for (long i = 1;i <= count;i++)
                    {
                        log("Got [key=_" + i + ", val=" + igniteCache.get("_" + i) + ']');
                    }
                    tx.commit();

                }
            }
            log("PersistenceStore example finished.");
            //ignite.destroyCache("dynamicCache");
            Thread.sleep(Integer.MAX_VALUE);
        }

    }


    private static void log(String msg) {
        LOGGER.info("\t" + msg);
    }
}
