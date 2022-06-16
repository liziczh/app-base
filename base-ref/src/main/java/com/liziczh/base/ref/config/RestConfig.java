package com.liziczh.base.ref.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * common
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/5/20 7:50 下午
 */
@Configuration
public class RestConfig {

    /**
     * 连接超时时间
     */
    @Value("${http.connectTimeout:1000}")
    private int connectTimeout;
    /**
     * 请求响应超时时间
     */
    @Value("${http.readTimeout:2000}")
    private int readTimeout;

    private ResponseErrorHandler responseErrorHandler;

    @PostConstruct
    private void init() {
        responseErrorHandler = initResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate() {
        return initRestTemplate(connectTimeout, readTimeout);
    }

    /**
     * 初始化RestTemplate
     *
     * @param
     * @return org.springframework.web.client.RestTemplate
     * @author chenzhehao
     * @date 2022/5/20 8:21 下午
     */
    private RestTemplate initRestTemplate(int connectTimeout, int readTimeout) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        // 错误处理器，否则无法获取responseEntity.getStatusCode()，而是直接抛异常
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }

    /**
     * 初始化错误码处理器
     *
     * @param
     * @return org.springframework.web.client.ResponseErrorHandler
     * @author chenzhehao
     * @date 2022/5/20 8:13 下午
     */
    private ResponseErrorHandler initResponseErrorHandler() {
        return new DefaultResponseErrorHandler() {
            @Override
            protected boolean hasError(HttpStatus statusCode) {
                return super.hasError(statusCode);
            }

            @Override
            public void handleError(ClientHttpResponse response) {
            }
        };
    }

}
