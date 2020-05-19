package com.liziczh.base.fastjson.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonUtils {
	private static final SerializeConfig CONFIG;
	static {
		CONFIG = new SerializeConfig();
		// 使用和json-lib兼容的日期输出格式
		CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
		// 使用和json-lib兼容的日期输出格式
		CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
	}
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
	/**
	 * object to jsonString
	 * @param object object
	 * @return json
	 */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, CONFIG, FEATURES);
	}
	/**
	 * object to jsonString no features
	 * @param object object
	 * @return json
	 */
	public static String toJSONStringNoFeatures(Object object) {
		return JSON.toJSONString(object, CONFIG);
	}
	/**
	 * jsonString to object
	 * @param jsonString jsonString
	 * @return object
	 */
	public static Object parseObject(String jsonString) {
		return JSON.parse(jsonString);
	}
	/**
	 * jsonString to object
	 * @param <T> object genericity
	 * @param jsonString jsonString
	 * @param clazz object
	 * @return object
	 */
	public static <T> T parseObject(String jsonString, Class<?> clazz) {
		return (T) JSON.parseObject(jsonString, clazz);
	}
	/**
	 * jsonString to Array
	 * @param jsonString jsonString
	 * @param clazz Array
	 * @return object[]
	 */
	public static <T> Object[] parseArray(String jsonString, Class<T> clazz) {
		return JSON.parseArray(jsonString, clazz).toArray();
	}
	/**
	 * jsonString to Array
	 * @param jsonString jsonString
	 * @return
	 */
	public static <T> Object[] parseArray(String jsonString) {
		return parseArray(jsonString, null);
	}
	/**
	 * jsonString to List
	 * @param jsonString jsonString
	 * @param clazz Class
	 * @return List
	 */
	public static <T> List<T> parseList(String jsonString, Class<T> clazz) {
		return JSON.parseArray(jsonString, clazz);
	}
	/**
	 * json to map
	 * @param json json
	 * @return map
	 */
	public static <K, V> Map<K, V> parseMap(String json) {
		return (Map<K, V>) JSONObject.parseObject(json);
	}
	/**
	 * map to jsonString
	 * @param map map
	 * @return jsonString
	 */
	public static <K, V> String mapToJsonString(Map<K, V> map) {
		return JSONObject.toJSONString(map);
	}
}
