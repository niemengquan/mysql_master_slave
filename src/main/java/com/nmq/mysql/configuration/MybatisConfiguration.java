package com.nmq.mysql.configuration;

import com.nmq.mysql.Enum.DataSourceType;
import com.nmq.mysql.datasource.MyAbstractRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niemengquan on 2017/11/13.
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@Slf4j
public class MybatisConfiguration extends MybatisAutoConfiguration{

    @Value("${druid.datasource.readSize}")
    private String dataSourceSize;

    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        return super.sqlSessionFactory(roundRobinDataSourceProxy());
    }

    /**
     * 多数据源轮流切换
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {
        int size=Integer.parseInt(dataSourceSize);
        MyAbstractRoutingDataSource proxyDataSource=new MyAbstractRoutingDataSource(size);
        Map<Object,Object> targetDataSource=new HashMap<>();
        DataSource writeDataSource=SpringContextHolder.getBean("writeDataSource", DataSource.class);
        //设置写数据源
        targetDataSource.put(DataSourceType.WRITE.getType(),writeDataSource);
        //设置读数据源
        for(int i=0;i<size;i++){
            targetDataSource.put(i,SpringContextHolder.getBean("readDataSource"+(i+1)));
        }
        proxyDataSource.setDefaultTargetDataSource(writeDataSource);
        proxyDataSource.setTargetDataSources(targetDataSource);
        return proxyDataSource;
    }
}
