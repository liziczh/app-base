package com.liziczh.base.common.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 逻辑删除枚举
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:50 下午
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {
    NORMAL(0, "正常"),
    DELETED(1, "删除");

    private Integer code;
    private String desc;

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


}
