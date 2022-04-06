package com.doubledimple.ignite.ignitecache.store.mode1;

import com.doubledimple.ignite.ignitecache.entity.pojo.Price;
import com.doubledimple.ignite.ignitecache.entity.query.PriceQuery;
import com.doubledimple.ignite.ignitecache.mapper.dao2.PriceMapper;
import com.doubledimple.ignite.ignitecache.utils.SpringContextUtil;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.HashMap;

public class PriceStore extends CacheStoreAdapter<String, Price> implements CacheStore<String, Price>,LifecycleAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceStore.class);

    public static final String PRICE_CACHE_NAME = PriceStore.class.getSimpleName() + "_price";

    public static final String PRICE_CACHE_NAME_KEY = PriceStore.class.getSimpleName() + "_price_";


    @Autowired
    private PriceMapper<Price, PriceQuery> priceMapper;

    @Override
    public Price load(String key) throws CacheLoaderException {
        Price price = priceMapper.selectByPrimaryKey(Long.valueOf(key));
        return price;
    }

    @Override
    public void write(Cache.Entry<? extends String, ? extends Price> entry) throws CacheWriterException {
        priceMapper.insert(entry.getValue());
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        priceMapper.deleteByPrimaryKey(Long.valueOf((String) key));
    }

    @Override
    public void start() throws IgniteException {
        priceMapper = SpringContextUtil.getBean(PriceMapper.class);
        LOGGER.info("jdbcTemplete:[{}]",priceMapper);
    }

    @Override
    public void stop() throws IgniteException {

    }
}
