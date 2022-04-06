package com.doubledimple.ignite.ignitecache.store.mode1;

import com.doubledimple.ignite.ignitecache.entity.pojo.Price;
import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.mapper.dao2.PriceMapper;
import com.doubledimple.ignite.ignitecache.utils.SpringContextUtil;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDB2Store extends CacheStoreAdapter<String, Price> implements CacheStore<String, Price>,LifecycleAware {

    private static Logger LOGGER = LoggerFactory.getLogger(MySqlDB2Store.class);

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public Price load(String key) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends String, ? extends Price> entry) throws CacheWriterException {
        Price price = entry.getValue();
        HashMap<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id",price.getId());
        parameterMap.put("price",price.getPrice());
        parameterMap.put("product_id",price.getProductId());

        priceMapper.insert(price);

    }

    @Override
    public void delete(Object key) throws CacheWriterException {

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
