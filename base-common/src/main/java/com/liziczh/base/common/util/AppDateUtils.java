package com.liziczh.base.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.liziczh.base.common.enums.DateFormatEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 日期时间工具类
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
@Slf4j
public class AppDateUtils {

    /**
     * 字符串转日期时间
     *
     * @param dateStr 日期时间字符串
     * @param pattern 格式
     * @return java.util.Date
     * @author chenzhehao
     * @date 2022/1/16 1:55 上午
     */
    public static Date stringToDate(String dateStr, String pattern) {
        if (StringUtils.isAnyBlank(dateStr, pattern)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            log.error("AppDateUtils.stringToDate, string parse to date error, string={}, pattern={}",
                    dateStr, pattern, e);
        }
        return date;
    }

    /**
     * 字符串转日期时间
     *
     * @param dateStr 日期时间字符串
     * @param format  格式
     * @return java.util.Date
     * @author chenzhehao
     * @date 2022/1/16 1:55 上午
     */
    public static Date stringToDate(String dateStr, DateFormatEnum format) {
        if (StringUtils.isAnyBlank(dateStr) || Objects.isNull(format)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.getPattern());
        Date date = new Date();
        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            log.error("AppDateUtils.stringToDate, string parse to date error, string={}, pattern={}",
                    dateStr, format.getPattern(), e);
        }
        return date;
    }

    /**
     * 日期时间转字符串
     *
     * @param date    日期时间
     * @param pattern 格式
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:56 上午
     */
    public static String dateToString(Date date, String pattern) {
        if (Objects.isNull(date) || StringUtils.isBlank(pattern)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 日期时间转字符串
     *
     * @param date   日期时间
     * @param format 格式
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:56 上午
     */
    public static String dateToString(Date date, DateFormatEnum format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.getPattern());
        return dateFormat.format(date);
    }

    /**
     * 判断当前时间是否在时间范围内
     *
     * @author chenzhehao
     * @date 2021/6/18 5:36 下午
     */
    public static Boolean betweenTimespan(String startTime, String endTime, String format) {
        String current = AppDateUtils.dateToString(new Date(), format);
        Date currentDate = AppDateUtils.stringToDate(current, format);
        Date startDate = AppDateUtils.stringToDate(startTime, format);
        Date endDate = AppDateUtils.stringToDate(endTime, format);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        if (startCalendar.after(endCalendar)) {
            // 时间范围跨天，不在范围内即在范围内，在范围内即不在范围内
            if (currentCalendar.after(startCalendar) || currentCalendar.before(endCalendar)) {
                return true;
            } else {
                return false;
            }
        } else {
            // 时间范围不跨天，在范围内即在范围内，不在范围内即不在范围内
            if (currentCalendar.after(startCalendar) && currentCalendar.before(endCalendar)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
