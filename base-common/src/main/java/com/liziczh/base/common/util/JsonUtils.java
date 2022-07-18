package com.liziczh.base.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

/**
 * JsonUtils
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 属性为NULL不参与序列化
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略缺省字段：关闭反序列化缺省字段失败
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 允许空对象：关闭序列化空对象失败
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 设置日期时间序列化格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置时区
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    /**
     * 对象转Json
     *
     * @param object
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 12:12 上午
     */
    public static String toJson(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("JsonUtils.toJson, object to json error", e);
        }
        return json;
    }

    /**
     * Json转对象
     *
     * @param json
     * @param clazz
     * @return T
     * @author chenzhehao
     * @date 2022/1/16 12:13 上午
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        T object = null;
        try {
            object = OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.error("JsonUtils.parseObject, json parse object error", e);
        }
        return object;
    }

    /**
     * Json转对象（支持泛型）
     *
     * @param json
     * @param typeReference
     * @return T
     * @author chenzhehao
     * @date 2022/1/16 12:13 上午
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        T object = null;
        try {
            object = OBJECT_MAPPER.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("JsonUtils.parseObject, json parse object error", e);
        }
        return object;
    }

    /**
     * Json转List
     *
     * @param json
     * @param element
     * @return java.util.List<T>
     * @author chenzhehao
     * @date 2022/1/16 12:51 上午
     */
    public static <T> List<T> parseList(String json, Class<T> element) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        List<T> result = null;
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, element);
            result = OBJECT_MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.error("JsonUtils.parseObject, json parse list error", e);
        }
        return result;
    }

    /**
     * Json转Map
     *
     * @param json
     * @param key
     * @param value
     * @return java.util.Map<K, V>
     * @author chenzhehao
     * @date 2022/1/16 12:55 上午
     */
    public static <K, V> Map<K, V> parseMap(String json, Class<K> key, Class<V> value) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, key, value);
        Map<K, V> result = null;
        try {
            result = OBJECT_MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.error("JsonUtils.parseObject, json parse map error", e);
        }
        return result;
    }
}
