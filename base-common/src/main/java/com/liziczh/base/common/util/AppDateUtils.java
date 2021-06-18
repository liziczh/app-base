package com.liziczh.base.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class AppDateUtils extends DateUtils {
    /**
     * 日期格式
     */
    public static enum DATE_FORMAT {
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
        TIME("HH:mm:ss");

        private String format;

        private DATE_FORMAT(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    /**
     * 日期单位
     */
    public static enum DATE_UNIT {
        YEAR("Y", "年"),
        MONTH("M", "月"),
        DAY("d", "日"),
        HOUR("h", "时"),
        MIN("m", "分"),
        SECOND("S", "秒"),
        MILLISECOND("s", "毫秒");

        private String unit;
        private String name;

        private DATE_UNIT(String unit, String name) {
            this.unit = unit;
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public String getName() {
            return name;
        }
    }

    public static Date stringToDate(String s, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date result = new Date();
		try {
            result = dateFormat.parse(s);
        } catch (Exception e) {
            log.error("DateFormat String parse to Date error:{}, string={}, pattern={}", e.getMessage(), s, pattern);
        }
        return result;
    }

    public static Date stringToDate(String s, DATE_FORMAT format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.getFormat());
		Date result = new Date();
		try {
			result = dateFormat.parse(s);
		} catch (Exception e) {
			log.error("DateFormat String parse to Date error:{}, string={}, format={}", e.getMessage(), s, format.getFormat());
		}
		return result;
	}

    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String dateToString(Date date, DATE_FORMAT format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.getFormat());
        return dateFormat.format(date);
    }
}
