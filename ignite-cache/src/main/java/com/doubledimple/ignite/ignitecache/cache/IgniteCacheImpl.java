package com.doubledimple.ignite.ignitecache.cache;
import org.apache.ibatis.cache.Cache;
import org.apache.ignite.IgniteCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ignite实现二级缓存
 */
public class IgniteCacheImpl implements Cache {

	private static final Logger logger = LoggerFactory.getLogger(IgniteCacheImpl.class);

	/**
	 * 缓存对象唯一标识
	 */
	private final String id;

	/**
	 * 用于事务性缓存操作的读写锁
	 */
	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * 缓存对象的是失效时间，60分钟
	 */
	private static final long EXPRIRE_TIME_IN_MINUT = 60;

	/**
	 * 生成key
	 */
	private final String COMMON_CACHE_KEY = "com:ryx:";

	@Autowired
	private IgniteCache igniteCache;

	public IgniteCacheImpl(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		final IgniteCache ignteCache = getIgnteCache();
		ignteCache.put(key.toString(), value);
	}

	@Override
	public Object getObject(Object key) {
		final IgniteCache ignteCache = getIgnteCache();
		return ignteCache.get(key.toString());
	}

	@Override
	public Object removeObject(Object key) {
		final IgniteCache ignteCache = getIgnteCache();
		return ignteCache.remove(key.toString());
	}

	@Override
	public void clear() {
		final IgniteCache ignteCache = getIgnteCache();
		ignteCache.clear();
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {

		return readWriteLock;
	}

	private IgniteCache getIgnteCache(){
		if(null== igniteCache){
			igniteCache = IgniteContext.getBean();
		}
		return igniteCache;
	}
}
