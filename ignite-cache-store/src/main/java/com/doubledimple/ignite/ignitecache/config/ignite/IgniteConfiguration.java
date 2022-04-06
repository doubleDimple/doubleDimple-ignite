package com.doubledimple.ignite.ignitecache.config.ignite;


import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class IgniteConfiguration {


    @Bean
    public Ignite instance(){
        org.apache.ignite.configuration.IgniteConfiguration cfg = new org.apache.ignite.configuration.IgniteConfiguration();
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

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("localhost"));
        spi.setIpFinder(ipFinder);
        cfg.setDiscoverySpi(spi);

        return Ignition.start(cfg);
    }
}
