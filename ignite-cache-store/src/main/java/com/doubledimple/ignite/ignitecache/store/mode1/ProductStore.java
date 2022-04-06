package com.doubledimple.ignite.ignitecache.store.mode1;

import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.entity.query.ProductQuery;
import com.doubledimple.ignite.ignitecache.mapper.dao1.ProductMapper;
import com.doubledimple.ignite.ignitecache.utils.SpringContextUtil;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.HashMap;
import java.util.Map;

public class ProductStore extends CacheStoreAdapter<String, Product> implements CacheStore<String, Product>,LifecycleAware {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductStore.class);

    public static final String PRODUCT_CACHE_NAME = PriceStore.class.getSimpleName() + "_product";
    public static final String PRODUCT_CACHE_NAME_KEY = PriceStore.class.getSimpleName() + "_product_";


    @Resource
    private ProductMapper<Product, ProductQuery> productMapper;

    @Override
    public Product load(String key) throws CacheLoaderException {
        Product product =  productMapper.selectByPrimaryKey(Long.valueOf(key));
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
    @SuppressWarnings("unchecked")
    public void start() throws IgniteException {
        productMapper = SpringContextUtil.getBean(ProductMapper.class);
        LOGGER.info("productMapper:[{}]",productMapper);
    }

    @Override
    public void stop() throws IgniteException {

    }
}
