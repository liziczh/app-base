package com.liziczh.base.common.util.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES加密解密
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:11 上午
 */
@Slf4j
public class AesUtils {

    private static final String CIPHER = "AES";

    /**
     * AES加密
     *
     * @param originalData 原数据
     * @param secretKey    密钥
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:13 上午
     */
    public static String encrypt(String originalData, String secretKey) {
        if (StringUtils.isAnyBlank(originalData, secretKey)) {
            return null;
        }
        String base64Str = "";
        try {
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey.getBytes(), CIPHER));
            byte[] bytes = cipher.doFinal(originalData.getBytes());
            base64Str = Base64.getEncoder().encodeToString(bytes);
            log.info("AesUtils.encrypt, success, originalData={}, secretKey={}, encryptData={}",
                    originalData, secretKey, base64Str);
        } catch (Exception e) {
            log.error("AesUtils.encrypt, error, originalData={}, secretKey={}", originalData, secretKey, e);
        }
        return base64Str;
    }

    /**
     * AES解密
     *
     * @param encryptData 加密数据
     * @param secretKey   密钥
     * @return java.lang.String
     * @author chenzhehao
     * @date 2022/1/16 1:16 上午
     */
    public static String decrypt(String encryptData, String secretKey) {
        if (StringUtils.isAnyBlank(encryptData, secretKey)) {
            return null;
        }
        String originalString = "";
        try {
            byte[] decodeBytes = Base64.getDecoder().decode(encryptData);
            Cipher cipher = Cipher.getInstance(CIPHER);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] original = cipher.doFinal(decodeBytes);
            originalString = new String(original);
            log.info("AesUtils.decrypt, success, encryptData={}, secretKey={}, originalData={}",
                    encryptData, secretKey, originalString);
        } catch (Exception e) {
            log.error("AesUtils.decrypt, error, encryptData={}, secretKey={}", encryptData, secretKey, e);
        }
        return originalString;
    }
}
