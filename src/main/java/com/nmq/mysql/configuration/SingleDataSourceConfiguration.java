package com.nmq.mysql.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 手动自定义配置单个数据源
 * Created by niemengquan on 2017/11/14.
 */
/*@Configuration
@PropertySource("classpath:application.properties")
@Slf4j*/
public class SingleDataSourceConfiguration {
    @Value("${spring.datasource.driverClassName}")
    private String DATABASE_DRIVER;
    @Value("${spring.datasource.password}")
    private String DATABASE_PASSWORD;
    @Value("${spring.datasource.url}")
    private String DATABASE_URL;
    @Value("${spring.datasource.username}")
    private String DATABASE_USERNAME;
    @Value("${spring.druid.initialSize}")
    private Integer DATABASE_INITIALSIZE;
    @Value("${spring.druid.minIdle}")
    private Integer DATABASE_MINIDLE;
    @Value("${spring.druid.maxActive}")
    private Integer DATABASE_MAXACTIVE;
    @Value("${spring.druid.maxWait}")
    private Integer DATABASE_MAXWAIT;
    @Value("${spring.druid.timeBetweenEvictionRunsMillis}")
    private Integer DATABASE_TIMEBETWEENEVICTIONRUNSMILLIS;
    @Value("${spring.druid.minEvictableIdleTimeMillis}")
    private Integer DATABASE_MINEVICTABLEIDLETIMEMILLIS;
    @Value("${spring.druid.testWhileIdle}")
    private Boolean DATABASE_TESTWHILEIDLE;
    @Value("${spring.druid.testOnBorrow}")
    private Boolean DATABASE_TESTONBORROW;
    @Value("${spring.druid.testOnReturn}")
    private Boolean DATABASE_TESTONRETURN;
    @Value("${spring.druid.poolPreparedStatements}")
    private Boolean DATABASE_POOLPREPAREDSTATEMENTS;
    @Value("${spring.druid.maxPoolPreparedStatementPerConnectionSize}")
    private Integer DATABASE_MAXPOOLPREPAREDSTATEMENTPERCONNECTIONSIZE;
    @Value("${spring.druid.filters}")
    private String DATABASE_FILTERS;
    @Value("${spring.druid.connectionProperties}")
    private String DATABASE_CONNECTIONPROPERTIES;


    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);
        dataSource.setInitialSize(DATABASE_INITIALSIZE);
        dataSource.setMinIdle(DATABASE_MINIDLE);
        dataSource.setMaxActive(DATABASE_MAXACTIVE);
        dataSource.setMaxWait(DATABASE_MAXWAIT);
        dataSource.setTimeBetweenEvictionRunsMillis(DATABASE_TIMEBETWEENEVICTIONRUNSMILLIS);
        dataSource.setMinEvictableIdleTimeMillis(DATABASE_MINEVICTABLEIDLETIMEMILLIS);
        dataSource.setTestWhileIdle(DATABASE_TESTWHILEIDLE);
        dataSource.setTestOnBorrow(DATABASE_TESTONBORROW);
        dataSource.setTestOnReturn(DATABASE_TESTONRETURN);
        dataSource.setPoolPreparedStatements(DATABASE_POOLPREPAREDSTATEMENTS);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(DATABASE_MAXPOOLPREPAREDSTATEMENTPERCONNECTIONSIZE);
        try {
            dataSource.setFilters(DATABASE_FILTERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] split = DATABASE_CONNECTIONPROPERTIES.split(";");
        Properties properties=new Properties();
        for(String str:split){
            properties.put(str.split("=")[0],str.split("=")[1]);
        }
        dataSource.setConnectProperties(properties);
        return dataSource;
    }
}
