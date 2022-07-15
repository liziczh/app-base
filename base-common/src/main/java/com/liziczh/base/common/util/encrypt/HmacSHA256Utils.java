package com.liziczh.base.common.util.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * HmacSHA256
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
@Slf4j
public class HmacSHA256Utils {
    public static final String hmacSHA256 = "HmacSHA256";

    /**
     * HmacSHA256加密
     *
     * @param originalData 原始数据
     * @param secretKey    密钥
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 2:01 上午
     */
    public static String encrypt(String originalData, String secretKey) {
        if (StringUtils.isAnyBlank(originalData, secretKey)) {
            return null;
        }
        try {
            Mac mac = Mac.getInstance(hmacSHA256);
            mac.init(new SecretKeySpec(secretKey.getBytes(), hmacSHA256));
            byte[] bytes = mac.doFinal(originalData.getBytes());
            String hex = bytesToHex(bytes);
            log.info("HmacSHA256.hmacSHA256, success, originalData={}, secretKey={}", originalData, secretKey);
            return hex;
        } catch (NoSuchAlgorithmException e) {
            log.error("HmacSHA256.hmacSHA256, no such algorithm", e);
        } catch (InvalidKeyException e) {
            log.error("HmacSHA256.hmacSHA256, invalid key", e);
        }
        return null;
    }

    /**
     * 字节数组转十六进制
     *
     * @param bytes 字节数组
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 2:09 上午
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        String tmp;
        for (int i = 0; bytes != null && i < bytes.length; i++) {
            tmp = Integer.toHexString(bytes[i] & 0XFF);
            if (tmp.length() == 1) {
                hexString.append('0');
            }
            hexString.append(tmp);
        }
        return hexString.toString().toUpperCase();
    }
}
