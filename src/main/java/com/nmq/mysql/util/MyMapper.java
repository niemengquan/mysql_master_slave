package com.nmq.mysql.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的Mapper
 * Created by niemengquan on 2017/11/9.
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
