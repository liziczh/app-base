package com.liziczh.base.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * common
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/6/15 10:14 下午
 */
@Slf4j
public class FileUtil {

    /**
     * URL生成临时文件
     *
     * @author chenzhehao
     * @date 2021/11/22 9:13 下午
     */
    public static File downloadTempFile(String url, String filePath) {
        log.info("FileUtil.downloadTempFile, start, url={}, filePath={}", url, filePath);
        AtomicReference<File> tmpFile = new AtomicReference<>(null);
        tmpFile.set(new File(filePath));
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
            FileUtils.copyInputStreamToFile(inputStream, tmpFile.get());
        } catch (IOException e) {
            log.error("FileUtil.downloadTempFile, error", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("FileUtil.downloadTempFile, inputStream close error", e);
                }
            }
        }
        log.info("FileUtil.downloadTempFile, end, url={}, filePath={}", url, filePath);
        return tmpFile.get();
    }

    /**
     * 删除临时文件
     *
     * @author chenzhehao
     * @date 2021/11/22 9:36 下午
     */
    public static boolean delTempFile(String tmpFilePath) {
        log.info("FileUtil.delTempFile, start, tmpFilePath={}",
                tmpFilePath);
        boolean delete = false;
        File tmpFile = new File(tmpFilePath);
        if (tmpFile.exists()) {
            delete = tmpFile.delete();
            log.info("FileUtil.delTempFile, success, delete={}, tmpFilePath={}",
                    delete, tmpFilePath);
        } else {
            log.error("FileUtil.delTempFile, file does not exist, tmpFilePath={}",
                    tmpFilePath);
        }
        log.info("FileUtil.delTempFile, end, delete={}, tmpFilePath={}, ",
                delete, tmpFilePath);
        return delete;
    }

    public static void main(String[] args) {
        String tmpFilePath = "1-tmp.pdf";
        String url = "";
        File file = downloadTempFile(url, tmpFilePath);
        boolean delete = delTempFile(tmpFilePath);
        System.out.println(delete);
    }

}
