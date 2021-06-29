/**
 * Baidu.com Inc.
 * Copyright (c) 2021 All Rights Reserved.
 */
package com.liziczh.base.common.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
     * @author chenzhehao
     * @date 2021/6/29 5:10 下午
     */
    private static String getHostname() {
        String hostname = "Unknown";
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException e) {
            log.info("Unknown Host error");
        }
        return hostname;
    }

    /**
     * 获取IP
     *
     * @author chenzhehao
     * @date 2021/6/29 5:10 下午
     */
    private static String getIp() {
        String ip = "127.0.0.1";
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
        } catch (UnknownHostException e) {
            log.info("Unknown Host error");
        }
        return ip;
    }
}
