package com.nmq.mysql.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by niemengquan on 2017/11/10.
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER=new ObjectMapper();
    private static final Logger LOGGER=LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 将json转化为java对象
     * @param json
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String json,Class<T> beanType) {
        T pojo = null;
        try {
            pojo = MAPPER.readValue(json,beanType);
        } catch (IOException e) {
            LOGGER.error("json转换为java对象失败,json:"+json,e);
        }
        return pojo;
    }

    /**
     * pojo转换为json字符串
     * @param pojo
     * @return
     */
    public static String pojoToJson(Object pojo){
        try {
            String result = MAPPER.writeValueAsString(pojo);
            return  result;
        } catch (JsonProcessingException e) {
            LOGGER.error("pojo转换为josn失败,pojo:"+pojo.toString(),e);
        }
        return "";
    }
}
