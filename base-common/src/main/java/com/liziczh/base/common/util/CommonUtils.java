package com.liziczh.base.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author zhehao.chen
 */
public class CommonUtils {
	/**
	 * 字符串 to List
	 * @param str 字符串
	 * @param separator 分隔符
	 * @return list
	 */
	public static List<String> string2List(String str, String separator) {
		if (StringUtils.isBlank(str)) {
			return new ArrayList<>();
		} else {
			return Arrays.asList(str.split(separator));
		}
	}
	/**
	 * List to 逗号分隔字符串
	 * @param list List
	 * @param separator 分隔符
	 * @return str
	 */
	public static String list2String(List<String> list, String separator) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			StringBuilder str = new StringBuilder();
			for (String element : list) {
				str.append(element).append(separator);
			}
			return str.toString();
		}
	}
}
