package com.liziczh.base.api.condition;

import lombok.Data;

/**
 * 排序条件
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/7/17 21:52
 */
@Data
public class SortCondition {
    private String columnName;
    private OrderTypeEnum orderType;

    /**
     * 排序类型
     */
    public enum OrderTypeEnum {
        ASC("ASC", "升序"),
        DESC("DESC", "降序");

        private String code;
        private String name;

        private OrderTypeEnum(String code, String name) {
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
