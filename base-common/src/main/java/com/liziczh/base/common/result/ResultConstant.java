package com.liziczh.base.common.result;

public class ResultConstant {
	/**
	 * 请求结果状态码
	 */
	public static enum RESULT_CODE {
		FAILED("0", "操作失败"),
		SUCCESS("1", "操作成功"),
		DENIED("3", "拒绝访问");
		private String code;
		private String name;

		private RESULT_CODE(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}
}
