package com.liziczh.base.common.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhehao.chen
 */
@Slf4j
public class HmacSHA256 {
	/**
	 * HmacSHA256加密
	 * @param data 数据
	 * @param secret 密钥
	 * @return java.lang.String
	 * @date 2021/3/17 13:59
	 */
	public static String hmacSHA256(String data, String secret){
		try {
			Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
			hmacSHA256.init(secretKey);
			byte[] bytes = hmacSHA256.doFinal(data.getBytes());
			return byteArrayToHexString(bytes);
		} catch (NoSuchAlgorithmException e) {
			log.error("No Algorithm");
		} catch (InvalidKeyException e) {
			log.error("Invalid Key");
		}
		return null;
	}
	private static String byteArrayToHexString(byte[] b) {
		StringBuilder hexString = new StringBuilder();
		String stmp;
		for (int i = 0; b!=null && i < b.length; i++) {
			stmp = Integer.toHexString(b[i] & 0XFF);
			if (stmp.length() == 1) {
				hexString.append('0');
			}
			hexString.append(stmp);
		}
		return hexString.toString().toUpperCase();
	}
}
