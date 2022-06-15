package com.liziczh.base.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日期时间格式枚举
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 1:46 上午
 */
@Getter
@AllArgsConstructor
public enum DateFormatEnum {
    DATE("yyyyMMdd"),
    H_DATE("yyyy-MM-dd"),
    S_DATE("yyyy/MM/dd"),
    D_DATE("yyyy.MM.dd"),
    DATETIME_TO_MIN("yyyyMMddHHmm"),
    H_DATETIME_TO_MIN("yyyy-MM-dd HH:mm"),
    S_DATETIME_TO_MIN("yyyy/MM/dd HH:mm"),
    D_DATETIME_TO_MIN("yyyy.MM.dd HH:mm"),
    DATETIME("yyyyMMddHHmmss"),
    H_DATETIME("yyyy-MM-dd HH:mm:ss"),
    S_DATETIME("yyyy/MM/dd HH:mm:ss"),
    D_DATETIME("yyyy.MM.dd HH:mm:ss"),
    DATE_TO_MONTH("yyyyMM"),
    H_DATE_TO_MONTH("yyyy-MM"),
    S_DATE_TO_MONTH("yyyy/MM"),
    D_DATE_TO_MONTH("yyyy.MM"),
    MONTH_TO_DAY("MMdd"),
    H_MONTH_TO_DAY("MM-dd"),
    S_MONTH_TO_DAY("MM/dd"),
    D_MONTH_TO_DAY("MM.dd"),
    MONTH_TO_MIN("MMdd HH:mm"),
    H_MONTH_TO_MIN("MM-dd HH:mm"),
    S_MONTH_TO_MIN("MM/dd HH:mm"),
    D_MONTH_TO_MIN("MM.dd HH:mm"),
    TIME_TO_MIN("HH:mm"),
    TIME("HH:mm:ss"),
    ;

    private String pattern;
}
