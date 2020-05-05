package com.liziczh.base.common.context;

import java.util.Map;

public class ContextThreadLocal {
	private static final ThreadLocal<Map<String, Object>> local = new ThreadLocal<Map<String, Object>>();

	public static Map<String, Object> getContext() {
		return local.get();
	}
	public static void setContext(Map<String, Object> contextMap) {
		if (local.get() != null) {
			Map<String, Object> currentContextMap = local.get();
			currentContextMap.putAll(contextMap);
			local.set(currentContextMap);
		} else {
			local.set(contextMap);
		}
	}
	public static void removeContext() {
		local.remove();
	}
	public static Object getContextParam(String contextKey) {
		Map<String, Object> context = local.get();
		if (context == null) {
			return null;
		} else {
			return context.get(contextKey);
		}
	}
	public static void setContextParam(String contextKey, Object contextValue) {
		Map<String, Object> context = local.get();
		if (context != null) {
			context.put(contextKey, contextValue);
		} else {
			System.out.println("===========context is null==========");
		}
	}
}
