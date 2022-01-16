package com.liziczh.base.common.enums;

import java.util.Objects;

/**
 * 逻辑删除枚举
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:50 下午
 */
public enum DeletedEnum {
    NORMAL(0, "正常"),
    DELETED(1, "删除");

    private Integer code;
    private String desc;

    DeletedEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DeletedEnum getEnum(Integer code) {
        if (Objects.nonNull(code)) {
            for (DeletedEnum value : DeletedEnum.values()) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
