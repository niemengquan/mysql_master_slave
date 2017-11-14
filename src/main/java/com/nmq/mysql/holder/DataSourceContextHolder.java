package com.nmq.mysql.holder;

import com.nmq.mysql.Enum.DataSourceType;

/**
 * Created by niemengquan on 2017/11/14.
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> localDataSource=new ThreadLocal<>();
    public static ThreadLocal<String> getLocalDataSource(){
        return localDataSource;
    }

    /**
     * 读可能对应到多个库
     */
    public static void setRead(){
        localDataSource.set(DataSourceType.READ.getType());
    }

    /**
     * 写库只有一个库
     */
    public static void setWrite(){
        localDataSource.set(DataSourceType.WRITE.getType());
    }

    /**
     * 获取当前线程中的数据源的类型
     * @return
     */
    public static String getJdbcType(){
        if(localDataSource.get()==null){
            setRead();
        }
        return localDataSource.get();
    }
}
