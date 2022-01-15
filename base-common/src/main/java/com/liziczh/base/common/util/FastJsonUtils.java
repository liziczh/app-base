package com.liziczh.base.common.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * FastJsonUtils
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
public class FastJsonUtils {

    private static final SerializeConfig CONFIG;

    private static final SerializerFeature[] FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    static {
        CONFIG = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    /**
     * 对象转Json
     *
     * @param object
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 2:16 上午
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, CONFIG, FEATURES);
    }

    /**
     * Json转对象
     *
     * @param json
     * @param clazz
     * @return T
     * @author chenzhehao
     * @date 2022/1/16 2:16 上午
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * Json转对象
     *
     * @param json
     * @param typeReference
     * @return T
     * @author chenzhehao
     * @date 2022/1/16 2:16 上午
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        return JSON.parseObject(json, typeReference);
    }

    /**
     * Json转List
     *
     * @param json
     * @param element
     * @return java.util.List<T>
     * @author chenzhehao
     * @date 2022/1/16 2:18 上午
     */
    public static <T> List<T> parseList(String json, Class<T> element) {
        return JSON.parseArray(json, element);
    }

    /**
     * Json转Map
     *
     * @param json
     * @return java.util.Map<K, V>
     * @author chenzhehao
     * @date 2022/1/16 2:20 上午
     */
    public static <K, V> Map<K, V> parseMap(String json) {
        return (Map<K, V>) JSON.parseObject(json);
    }
}
