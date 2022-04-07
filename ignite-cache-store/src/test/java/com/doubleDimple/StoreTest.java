package com.doubleDimple;


import com.doubledimple.ignite.ignitecache.IgniteCacheStoreApplication;
import com.doubledimple.ignite.ignitecache.utils.CacheConfigurationUtils;
import com.doubledimple.ignite.ignitecache.dtx.IgniteDisTransTemplate;
import com.doubledimple.ignite.ignitecache.entity.pojo.Price;
import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.store.mode1.ProductStore;
import com.doubledimple.ignite.ignitecache.store.mode1.PriceStore;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.doubledimple.ignite.ignitecache.store.mode1.PriceStore.PRICE_CACHE_NAME;
import static com.doubledimple.ignite.ignitecache.store.mode1.PriceStore.PRICE_CACHE_NAME_KEY;
import static com.doubledimple.ignite.ignitecache.store.mode1.ProductStore.PRODUCT_CACHE_NAME;
import static com.doubledimple.ignite.ignitecache.store.mode1.ProductStore.PRODUCT_CACHE_NAME_KEY;
import static org.apache.ignite.transactions.TransactionConcurrency.PESSIMISTIC;
import static org.apache.ignite.transactions.TransactionIsolation.REPEATABLE_READ;

@SpringBootTest(classes = IgniteCacheStoreApplication.class)
public class StoreTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreTest.class);

    @Autowired
    private IgniteDisTransTemplate igniteDisTransTemplate;

    @Autowired
    private Ignite ignite;

    @Test
    public void testmysql(){

        IgniteCache<String, Product> productIgniteCache = ignite.
                        getOrCreateCache(CacheConfigurationUtils.
                        getCacheConfiguration(Product.class, PRODUCT_CACHE_NAME, ProductStore.class));

        IgniteCache<String, Price> priceIgniteCache = ignite.
                        getOrCreateCache(CacheConfigurationUtils.
                        getCacheConfiguration(Price.class, PRICE_CACHE_NAME, PriceStore.class));

        int i= 1;
        Product product = new Product();
        product.setName("name_"+i);

        Price price = new Price();
        price.setPrice(new BigDecimal(100));
        price.setProductId(1L);

        igniteDisTransTemplate.disTrans(PESSIMISTIC, REPEATABLE_READ, () -> {
            productIgniteCache.put(PRODUCT_CACHE_NAME_KEY+i,product);
            priceIgniteCache.put(PRICE_CACHE_NAME_KEY+i,price);
        });

        LOGGER.info("business handler complete.....");

    }
}
