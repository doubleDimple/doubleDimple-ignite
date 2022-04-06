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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import static com.doubledimple.ignite.ignitecache.config.datasource.constant.MAPPER_XML_LOCATION_DS1;

@Configuration
@MapperScan(basePackages = constant.MAPPER_DS1, sqlSessionFactoryRef = "ds1SqlSessionFactory")
public class DataSourceConfigDs1 {

    @Value("${spring.datasource.ds1.url}")
    private String url;
    @Value("${spring.datasource.ds1.username}")
    private String username;
    @Value("${spring.datasource.ds1.username}")
    private String password;

     @Bean(name="ds1DataSource")
     public DataSource ds1DataSource() {
         DruidDataSource dataSource = new DruidDataSource();
         dataSource.setUrl(url);
         dataSource.setUsername(username);
         dataSource.setPassword(password);
         return dataSource;
     }

    @Bean
    @Primary
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("ds1DataSource") DataSource dataSource) {
         return new DataSourceTransactionManager(dataSource);
     }

    @Bean(value = "namedParameterJdbcTemplateDs1")
    @Primary
     public NamedParameterJdbcTemplate ds1JdbcTemplete(@Qualifier("ds1DataSource") DataSource dataSource){
         return new NamedParameterJdbcTemplate(dataSource);
     }

     @Bean
     @Primary
     public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource ds1DataSource) throws Exception {
         final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
         sessionFactory.setDataSource(ds1DataSource);
         sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                                 .getResources(MAPPER_XML_LOCATION_DS1));
         return sessionFactory.getObject();
     }
}
