package com.doubledimple.ignite.ignitecache.store;

import com.doubledimple.ignite.ignitecache.entity.pojo.Product;
import com.doubledimple.ignite.ignitecache.utils.SpringContextUtil;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.store.CacheStoreAdapter;
import org.apache.ignite.lifecycle.LifecycleAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySqlDBStore extends CacheStoreAdapter<String, Product> implements LifecycleAware {

    private static Logger LOGGER = LoggerFactory.getLogger(MySqlDBStore.class);

    private static final String INSERT_SQL = "INSERT INTO product(id,name) VALUES (:id,:name)";

    private static final String SELECT_SQL_ID = "SELECT * FROM product WHERE id=?";

    private static final String DELETE_SQL = "DELETE FROM product WHERE id= ?";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Product load(String key) throws CacheLoaderException {
        Map<String, Object> inputParam = new HashMap<>();
        inputParam.put("id", key);
        return jdbcTemplate.queryForObject(SELECT_SQL_ID,inputParam, (rs, i) -> new Product(rs.getLong(1), rs.getString(2)));
    }

    @Override
    public void write(Cache.Entry<? extends String, ? extends Product> entry) throws CacheWriterException {
         Product product = entry.getValue();
         HashMap<String, Object> parameterMap = new HashMap<>();
         parameterMap.put("id",product.getId());
         parameterMap.put("name",product.getName());

         jdbcTemplate.update(INSERT_SQL,parameterMap);
    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        Map<String, String> deleteMap = new HashMap<>();
        deleteMap.put("id", (String) key);
        jdbcTemplate.update(DELETE_SQL, deleteMap);
    }

    @Override
    public void start() throws IgniteException {
        jdbcTemplate = SpringContextUtil.getBean(NamedParameterJdbcTemplate.class);
        LOGGER.info("jdbcTemplete:[{}]",jdbcTemplate);
    }

    @Override
    public void stop() throws IgniteException {

    }
}
