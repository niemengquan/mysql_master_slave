package com.nmq.mysql.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by niemengquan on 2017/11/14.
 */
@Configuration
@EnableTransactionManagement
@Slf4j
public class MyDataSourceTransactionAutoConfiguration extends DataSourceTransactionManagerAutoConfiguration {
    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，
     * 只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的
     * 数据源一致即可，否则事务管理会不起作用。
     * @return
     */
    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(){
        log.info("-------------------- transactionManager init ---------------------");
        return new DataSourceTransactionManager(SpringContextHolder.getBean("roundRobinDataSourceProxy"));
    }
}
