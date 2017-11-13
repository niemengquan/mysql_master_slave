package com.nmq.mysql.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by niemengquan on 2017/11/13.
 */
@Configuration
@Slf4j
public class DataSourceConfiguration {
    @Value("${druid.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.datasource.write")
    public DataSource writeDateSource(){
        log.info("============writeDateSource init============");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "druid.datasource.read1")
    public DataSource readDataSourceOne(){
        log.info("============writeDateSource init============");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource2")
    @ConfigurationProperties(prefix = "druid.datasource.read2")
    public DataSource readDataSourceTwo(){
        log.info("============writeDateSource init============");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
}
