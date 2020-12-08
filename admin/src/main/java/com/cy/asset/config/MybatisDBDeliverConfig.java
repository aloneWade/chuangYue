package com.cy.asset.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: LuoYuanchun
 * Date: 17/12/6
 * Time: 下午8:51
 */
//@Configuration
//@MapperScan(basePackages = MybatisDBDeliverConfig.PACKAGE, sqlSessionFactoryRef = "driverSqlSessionFactory")
public class MybatisDBDeliverConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "bike.driver.dao";
    static final String MAPPER_LOCATION = "classpath*:bike/driver/**/mapper/*.xml";
    static final String CONFIG_LOCATION = "classpath:mybatis-config.xml";

    @Value("${spring.datasource.secondary.url}")
    private String url;

    @Value("${spring.datasource.secondary.username}")
    private String user;

    @Value("${spring.datasource.secondary.password}")
    private String password;

    @Value("${spring.datasource.secondary.driver-class-name}")
    private String driverClass;

    @Bean(name = "driverDataSource")
    public DataSource secondaryDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "driverTransactionManager")
    public DataSourceTransactionManager secondaryTransactionManager() {
        return new DataSourceTransactionManager(secondaryDataSource());
    }

    @Bean(name = "driverSqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("driverDataSource") DataSource driverDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(driverDataSource);
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(CONFIG_LOCATION));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}