package com.doubledimple.ignite.ignitecache.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = constant.MAPPER_DS2, sqlSessionFactoryRef = "ds2SqlSessionFactory")
public class DataSourceConfigDs2 {

    @Value("${spring.datasource.ds2.url}")
    private String url;
    @Value("${spring.datasource.ds2.username}")
    private String username;
    @Value("${spring.datasource.ds2.password}")
    private String password;

     @Bean(name="ds2DataSource")
     public DataSource ds2DataSource() {
         DruidDataSource dataSource = new DruidDataSource();
         dataSource.setUrl(url);
         dataSource.setUsername(username);
         dataSource.setPassword(password);
         return dataSource;
     }

    @Bean
    @Primary
    public DataSourceTransactionManager ds2TransactionManager(@Qualifier("ds2DataSource") DataSource dataSource) {
         return new DataSourceTransactionManager(dataSource);
     }

     @Bean
     @Primary
     public SqlSessionFactory ds2SqlSessionFactory(@Qualifier("ds2DataSource") DataSource ds2DataSource) throws Exception {
         final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
         sessionFactory.setDataSource(ds2DataSource);
         sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                                 .getResources(constant.MAPPER_XML_LOCATION_DS2));
         return sessionFactory.getObject();
     }
}
