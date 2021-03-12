package com.liziczh.base.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

/**
 * 加密解密
 * @author zhehao.chen
 */
@Slf4j
public class DigestUtils {
	private static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static MessageDigest getInstance(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Could not find MessageDigest with algorithm [" + algorithm + "]", e);
		}
	}
	public static String md5DigestAsHex(String data) {
		return digestAsHex("MD5",data.getBytes());
	}
	public static byte[] md5Digest(String data) {
		return getInstance("MD5").digest(data.getBytes());
	}
	public static String digestAsHex(String algorithm, byte[] data) {
		return new String(encodeHex(digest(algorithm, data)));
	}
	public static byte[] digest(String algorithm, byte[] bytes) {
		return getInstance(algorithm).digest(bytes);
	}
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
