package com.liziczh.base.api.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortCondition {
    private String columnName;
    private String orderType;

    /**
     * 排序类型
     */
    public enum ORDER {
        ASC("ASC", "升序"),
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
