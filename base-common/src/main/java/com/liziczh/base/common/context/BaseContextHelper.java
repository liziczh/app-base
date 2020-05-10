package com.liziczh.base.common.context;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseContextHelper extends ContextThreadLocal {
	public enum CONTEXT_TYPE {
		STATIC("STATIC", "静态CONTEXT"),
		DYNAMIC("DYNAMIC", "动态CONTEXT"),
		CUSTOM("CUSTOM", "自定义CONTEXT"),
		DATA("USERDATA", "数据CONTEXT");
		private String status;
		private String statusName;

		private CONTEXT_TYPE(String status, String statusName) {
			this.status = status;
			this.statusName = statusName;
		}
		public String getCode() {
			return status;
		}
		public String getName() {
			return statusName;
		}
	}

	/**
	 * Static Area
	 * @return
	 */
	public static String getSessionId() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.STATIC.getCode());
		if (context != null) {
			return (String) context.get(BaseContextProperties.S_SESSION_ID);
		} else {
			return null;
		}
	}
	public static String getIpAddress() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.STATIC.getCode());
		if (context != null) {
			return (String) context.get(BaseContextProperties.S_IP_ADDRESS);
		} else {
			return null;
		}
	}
	public static String getAppPath() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.STATIC.getCode());
		if (context != null) {
			return (String) context.get(BaseContextProperties.S_REAL_PATH);
		} else {
			return null;
		}
	}
	/**
	 * Custom Area
	 * @return
	 */
	public static String getAccessToken() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null && context.get(BaseContextProperties.S_ACCESS_TOKEN) != null) {
			return context.get(BaseContextProperties.S_ACCESS_TOKEN).toString();
		} else {
			return null;
		}
	}
	public static Object getTokenInfo() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null) {
			return context.get(BaseContextProperties.O_TOKEN_INFO);
		} else {
			return null;
		}
	}
	public static Object getUserInfo() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null) {
			return context.get(BaseContextProperties.O_USER_INFO);
		} else {
			return null;
		}
	}
	public static String getUserLoginId() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null && context.get(BaseContextProperties.S_USER_LOGIN_ID) != null) {
			return context.get(BaseContextProperties.S_USER_LOGIN_ID).toString();
		} else {
			return null;
		}
	}
	public static String getUserId() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null && context.get(BaseContextProperties.S_USER_ID) != null) {
			return context.get(BaseContextProperties.S_USER_ID).toString();
		} else {
			return null;
		}
	}
	public static String getComefrom() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null && context.get(BaseContextProperties.S_COME_FROM) != null) {
			return context.get(BaseContextProperties.S_COME_FROM).toString();
		} else {
			return null;
		}
	}
	public static String getClientVersion() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null && context.get(BaseContextProperties.S_CLIENT_VERSION) != null) {
			return context.get(BaseContextProperties.S_CLIENT_VERSION).toString();
		} else {
			return null;
		}
	}
	public static String getChannelCode() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.CUSTOM.getCode());
		if (context != null && context.get(BaseContextProperties.S_CHANNEL_CODE) != null) {
			return context.get(BaseContextProperties.S_CHANNEL_CODE).toString();
		} else {
			return null;
		}
	}
	/**
	 * Dynamic Area
	 * @return
	 */
	public static String getRefererUrl() {
		Map<String, Object> context = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.DYNAMIC.getCode());
		if (context != null && context.get(BaseContextProperties.S_REFERER) != null) {
			return context.get(BaseContextProperties.S_REFERER).toString();
		} else {
			return null;
		}
	}
	/**
	 * Userdata Area
	 */
	@SuppressWarnings("unchecked")
	public static void putDataValue(String key, Object value) {
		Map<String, Object> runtimeMap = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.DATA.getCode());
		if (runtimeMap != null) {
			runtimeMap.put(key, value);
		} else {
			runtimeMap = new HashMap<String, Object>();
			runtimeMap.put(key, value);
			ContextThreadLocal.setContextParam(CONTEXT_TYPE.DATA.getCode(), runtimeMap);
		}
	}
	@SuppressWarnings("unchecked")
	public static Object getDataValue(String key) {
		Map<String, Object> runtimeMap = (Map<String, Object>) ContextThreadLocal.getContextParam(CONTEXT_TYPE.DATA.getCode());
		if (runtimeMap != null) {
			return runtimeMap.get(key);
		} else {
			return null;
		}
	}
}
