package com.nmq.mysql.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by niemengquan on 2017/11/13.
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter {
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

    /**
     * 配置mvc静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/*");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/*");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/*");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responBodyConverter());
        converters.add(mappingJackson2HttpMessageConverter());
    }

    /**
     * 解决controller的返回类型是：text/html;charset=UTF-8
     * 中文乱码问题
     *
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
        return converter;
    }

    /**
     * 避免IE执行时,返回JSON出现下载文件的问题
     *
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(
                MediaType.parseMediaType("text/plain;charset=utf-8"),
                MediaType.APPLICATION_JSON_UTF8,
                new MediaType("application","x-www-form-urlencoded")

        ));
        return converter;
    }
}
