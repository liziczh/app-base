package com.liziczh.base.common.condition;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortCondition implements Serializable {
	private static final long serialVersionUID = 2685484123703694389L;
	private String cloName;
	private String orderType;
	/**
	 * 排序类型
	 */
	public enum ORDER {
		AEC("AEC", "升序"),
		DESC("DESC", "降序");

		private String code;
		private String name;

		private ORDER(String code, String name) {
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
