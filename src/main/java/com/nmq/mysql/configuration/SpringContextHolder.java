package com.nmq.mysql.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 该类如果放在holder包下面会出现：com.nmq.mysql.datasource.MyAbstractRoutingDataSource#determineCurrentLookupKey()方法先执行
 * 而。SpringContextHolder 还没有被Spring管理的情况，所以暂时将其放入在configuration包下。
 * Created by niemengquan on 2017/11/14.
 */
@Component
public class SpringContextHolder implements ApplicationContextAware{
    /**
     * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext.
     */
    private static ApplicationContext context;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.context=applicationContext;
    }

    /**
     * 获取ApplicationContext
     * @return ApplicationContext 实例
     */
    public static ApplicationContext getApplicationContext(){
        return SpringContextHolder.context;
    }

    /**
     * 获取Spring 容器bean
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name,Class<T> requiredType){
        return SpringContextHolder.context.getBean(name,requiredType);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        return (T) SpringContextHolder.context.getBean(name);
    }
}
