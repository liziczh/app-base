package com.liziczh.base.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 摘要工具类
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
@Slf4j
public class DigestUtils {
    public static final String MD5 = "MD5";
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 生成MD5十六进制摘要信息
     *
     * @param data 数据
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:44 上午
     */
    public static String md5DigestAsHex(String data) {
        return digestAsHex(data.getBytes(), MD5);
    }

    /**
     * 生成MD5摘要信息
     *
     * @param data 数据
     * @return byte[]
     * @author chenzhehao
     * @date 2022/1/16 1:44 上午
     */
    public static byte[] md5Digest(String data) {
        return digest(data.getBytes(), MD5);
    }

    /**
     * 生成十六进制摘要信息
     *
     * @param data      数据
     * @param algorithm 算法
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:44 上午
     */
    public static String digestAsHex(byte[] data, String algorithm) {
        return new String(encodeHex(digest(data, algorithm)));
    }

    /**
     * 生成摘要信息
     *
     * @param bytes     数据
     * @param algorithm 算法
     * @return byte[]
     * @author chenzhehao
     * @date 2022/1/16 1:36 上午
     */
    public static byte[] digest(byte[] bytes, String algorithm) {
        if (StringUtils.isBlank(algorithm) || bytes == null) {
            return null;
        }
        byte[] digest = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            digest = messageDigest.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("DigestUtils.digest, could not find MessageDigest with algorithm [{}]", algorithm, e);
        }
        return digest;
    }

    /**
     * 字节数组转十六进制
     *
     * @param bytes
     * @return char[]
     * @author chenzhehao
     * @date 2022/1/16 1:35 上午
     */
    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }
        return chars;
    }
}
