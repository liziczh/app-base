package com.liziczh.base.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

/**
 * HostUtils
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/6/29 5:17 下午
 */
@Slf4j
public class HostUtils {

    /**
     * 获取主机名
     *
     * @param
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:12 上午
     */
    public static String getHostname() {
        String hostname = "unknown";
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            log.info("HostUtils.getHostname, unknown host error", e);
        }
        return hostname;
    }

    /**
     * 获取IP
     *
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:12 上午
     */
    public static String getIp() {
        String ip = "127.0.0.1";
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            log.info("HostUtils.getIp, unknown host error", e);
        }
        return ip;
    }
}
