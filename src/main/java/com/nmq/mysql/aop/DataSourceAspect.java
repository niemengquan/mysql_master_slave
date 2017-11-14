package com.nmq.mysql.aop;

import com.nmq.mysql.holder.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行,
 * 因为：com.nmq.mysql.configuration.MyDataSourceTransactionAutoConfiguration 上的注解EnableTransactionManagement 的优先级是最小的，
 * 所以这里我们的优先级只要大于它就行
 * Created by niemengquan on 2017/11/14.
 */
@Aspect
@Component
@Slf4j
public class DataSourceAspect implements PriorityOrdered{

    /**
     * 根据注解事务的读写设置采用的数据源
     * @param joinPoint
     */
    @Before("execution(* com.nmq.mysql.service..*.*(..))")
   /* @Before("@annotation(org.springframework.transaction.annotation.Transactional)")*/
    public void setDataSourceTypeByTransctionAnnotationType(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        if(signature instanceof  MethodSignature){
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = methodSignature.getMethod();
            Object target = joinPoint.getTarget();
            Method method = target.getClass().getMethod(targetMethod.getName(),targetMethod.getParameterTypes());
            Transactional[] annotationsByType = method.getAnnotationsByType(Transactional.class);
            if(annotationsByType.length==0){
                //设置读库
                DataSourceContextHolder.setRead();
                log.info("---------------dataSource切换到：Read-------------------");
                return ;
            }
            boolean readOnly = annotationsByType[0].readOnly();
            if(readOnly){
                //设置读库
                DataSourceContextHolder.setRead();
                log.info("------------------------dataSource切换到：Read---------------------");
            }else {
                //设置写库
                DataSourceContextHolder.setWrite();
                log.info("--------------------------dataSource切换到：write--------------------");
            }
        }
    }

    @Override
    public int getOrder() {
        /**
         *这里设置的值只要比@EnableTransactionManagement 的order值大就可以
         * 查看api可知@EnableTransactionManagement的order的默认是为最小值int order() default Ordered.LOWEST_PRECEDENCE;
         *
         * int HIGHEST_PRECEDENCE = -2147483648;
         * int LOWEST_PRECEDENCE = 2147483647;
         */
        return 2147483646;
    }
}
