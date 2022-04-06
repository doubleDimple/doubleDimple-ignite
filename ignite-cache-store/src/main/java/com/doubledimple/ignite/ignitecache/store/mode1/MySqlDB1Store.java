package com.doubledimple.ignite.ignitecache.store.mode1;

import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.mapper.dao1.ProductMapper;
import com.doubledimple.ignite.ignitecache.utils.SpringContextUtil;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDB1Store extends CacheStoreAdapter<String, Product> implements CacheStore<String, Product>,LifecycleAware {

    private static Logger LOGGER = LoggerFactory.getLogger(MySqlDB1Store.class);


    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product load(String key) throws CacheLoaderException {
        Map<String, Object> inputParam = new HashMap<>();
        inputParam.put("id", key);
        Product product = (Product) productMapper.selectByPrimaryKey(Long.valueOf(key));
        return product;
    }

    @Override
    public void write(Cache.Entry<? extends String, ? extends Product> entry) throws CacheWriterException {
        productMapper.insert(entry.getValue());
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        productMapper.deleteByPrimaryKey(Long.valueOf((String) key));
    }

    @Override
    public void start() throws IgniteException {
        productMapper = SpringContextUtil.getBean(ProductMapper.class);
        LOGGER.info("jdbcTemplete:[{}]",productMapper);
    }

    @Override
    public void stop() throws IgniteException {

    }
}
