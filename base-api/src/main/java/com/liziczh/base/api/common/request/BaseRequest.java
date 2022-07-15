package com.liziczh.base.api.common.request;

import lombok.*;

/**
 * 请求体
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/12/10 11:06 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseRequest<T> {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 数据
     */
    private T data;

}
