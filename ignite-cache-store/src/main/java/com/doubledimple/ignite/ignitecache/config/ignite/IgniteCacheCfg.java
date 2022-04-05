package com.doubledimple.ignite.ignitecache.config.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

//@Configuration
//@EnableIgniteRepositories
public class IgniteCacheCfg {

	@Bean
	public Ignite igniteInstance(){

		IgniteConfiguration cfg = new IgniteConfiguration();
		//cfg.setClientMode(true);

		cfg.setPeerClassLoadingEnabled(true);
		//失败检测时间
		cfg.setFailureDetectionTimeout(6000);
		//公共线程池大小
		cfg.setPublicThreadPoolSize(512);
		//系统线程池代销
		cfg.setSystemThreadPoolSize(512);
		//源线程大小
		cfg.setStripedPoolSize(512);
		//数据流线程大小
		cfg.setDataStreamerThreadPoolSize(512);
		//查询线程大小
		cfg.setQueryThreadPoolSize(512);
		//平衡线程池大小
		cfg.setRebalanceThreadPoolSize(15);
		//用户验证开启状态
		cfg.setAuthenticationEnabled(false);
		cfg.setMetricsLogFrequency(0);

		//数据存储配置
		DataStorageConfiguration data = new DataStorageConfiguration();
		data.setConcurrencyLevel(72);

		cfg.setDataStorageConfiguration(data);

		TcpDiscoverySpi spi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
		spi.setIpFinder(ipFinder);
		cfg.setDiscoverySpi(spi);

		Ignite ignite = Ignition.start(cfg);
		return ignite;
	}


}
