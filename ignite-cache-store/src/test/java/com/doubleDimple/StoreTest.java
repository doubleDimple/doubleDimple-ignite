package com.doubleDimple;


import com.doubledimple.ignite.ignitecache.IgniteCacheStoreApplication;
import com.doubledimple.ignite.ignitecache.dtx.CacheConfigurationUtils;
import com.doubledimple.ignite.ignitecache.dtx.IgniteDisTransTemplate;
import com.doubledimple.ignite.ignitecache.entity.pojo.Price;
import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.store.mode1.MySqlDB1Store;
import com.doubledimple.ignite.ignitecache.store.mode1.MySqlDB2Store;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.apache.ignite.transactions.TransactionConcurrency.PESSIMISTIC;
import static org.apache.ignite.transactions.TransactionIsolation.REPEATABLE_READ;

@SpringBootTest(classes = IgniteCacheStoreApplication.class)
public class StoreTest {

    private static final String PRODUCT_CACHE_NAME = StoreTest.class.getSimpleName() + "_product";
    private static final String PRICE_CACHE_NAME = StoreTest.class.getSimpleName() + "_price";

    private static Logger LOGGER = LoggerFactory.getLogger(StoreTest.class);


    @Autowired
    private IgniteDisTransTemplate igniteDisTransTemplate;


    @Autowired
    private Ignite ignite;

    @Test
    public void testmysql(){

        IgniteCache<String, Product> productIgniteCache = ignite.getOrCreateCache(CacheConfigurationUtils.getCacheConfiguration(Product.class, PRODUCT_CACHE_NAME, MySqlDB1Store.class));

        IgniteCache<String, Price> priceIgniteCache = ignite.getOrCreateCache(CacheConfigurationUtils.getCacheConfiguration(Price.class, PRICE_CACHE_NAME, MySqlDB2Store.class));


        igniteDisTransTemplate.disTrans(PESSIMISTIC, REPEATABLE_READ, () -> {
            int i= 1;
            Product product = new Product();
            product.setName("name_"+i);
            productIgniteCache.put("_"+i,product);

            Price price = new Price();
            price.setPrice(new BigDecimal(100));
            price.setProductId(1L);
            priceIgniteCache.put("_"+i,price);
        });

    }
}
