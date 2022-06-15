package com.liziczh.base.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码枚举
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:50 下午
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    SUCCESS("100200", "成功"),
    ERROR("100500", "系统错误"),
    BAD_REQUEST("100501", "请求参数错误"),

    ;

    private String code;
    private String desc;
}
