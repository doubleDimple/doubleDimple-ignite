package com.doubledimple.ignite.ignitecache.cache;


import com.doubledimple.ignite.ignitecache.config.ignite.IgniteCacheCfg;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class IgniteContext {

	private static IgniteContext igniteContext;

	@Autowired
	private IgniteCacheCfg igniteCacheCfg;

	@PostConstruct
	public void init(){
		igniteContext = this;
		igniteContext.igniteCacheCfg = igniteCacheCfg;
	}

	public static IgniteCache getBean(){
		IgniteCache igniteCache = igniteContext.igniteCacheCfg.igniteCache();
		return igniteCache;
	}
}

