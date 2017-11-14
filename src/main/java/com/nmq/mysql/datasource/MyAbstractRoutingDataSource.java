package com.nmq.mysql.datasource;

import com.nmq.mysql.Enum.DataSourceType;
import com.nmq.mysql.holder.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by niemengquan on 2017/11/14.
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource{
    private final int dataSourceNumber;
    private AtomicInteger count=new AtomicInteger(-1);//线程安全的类

    public MyAbstractRoutingDataSource(int dataSourceNumber){
        this.dataSourceNumber=dataSourceNumber;
    }

    /**
     * 采用轮询的方式，设置当前采用的读库
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String jdbcType = DataSourceContextHolder.getJdbcType();
        if(jdbcType.equals(DataSourceType.WRITE.getType())){
            return DataSourceType.WRITE.getType();
        }
        //读库选择
        int number = count.incrementAndGet();
        //得到的小标为：0，1，2，3。。。
        int lookupKey =number % dataSourceNumber;
        if(count.get()>9999){
            count.set(-1);//还原，避免count过大
        }
        return new Integer(lookupKey);
    }
}
