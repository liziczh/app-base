package com.liziczh.base.common.response;

import com.liziczh.base.api.common.response.BaseResponse;
import com.liziczh.base.common.enums.ResponseCodeEnum;
import lombok.Data;

/**
 * 响应体构建器
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2021/12/10 11:06 下午
 */
@Data
public class ResponseBuilder<T> {

    /**
     * 通用操作成功
     *
     * @param
     * @return com.liziczh.mvc.mesh.api.common.response.BaseResponse<T>
     * @author chenzhehao
     * @date 2022/1/16 9:47 下午
     */
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>()
                .code(ResponseCodeEnum.SUCCESS.getCode())
                .msg(ResponseCodeEnum.SUCCESS.getDesc());
    }

    /**
     * 通用操作成功
     *
     * @param data
     * @return com.liziczh.mvc.mesh.api.common.response.BaseResponse<T>
     * @author chenzhehao
     * @date 2022/1/16 9:47 下午
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>()
                .code(ResponseCodeEnum.SUCCESS.getCode())
                .msg(ResponseCodeEnum.SUCCESS.getDesc())
                .data(data);
    }

    /**
     * 通用系统错误
     *
     * @param
     * @return com.liziczh.mvc.mesh.api.common.response.BaseResponse<T>
     * @author chenzhehao
     * @date 2022/1/16 9:47 下午
     */
    public static <T> BaseResponse<T> error() {
        return new BaseResponse<T>()
                .code(ResponseCodeEnum.ERROR.getCode())
                .msg(ResponseCodeEnum.ERROR.getDesc());
    }

    /**
     * 自定义错误
     *
     * @param codeCodeEnum
     * @return com.liziczh.mvc.mesh.api.common.response.BaseResponse<T>
     * @author chenzhehao
     * @date 2022/1/16 9:47 下午
     */
    public static <T> BaseResponse<T> error(ResponseCodeEnum codeCodeEnum) {
        return new BaseResponse<T>()
                .code(codeCodeEnum.getCode())
                .msg(codeCodeEnum.getDesc());
    }

    /**
     * 自定义错误
     *
     * @author chenzhehao
     * @date 2021/12/10 11:12 下午
     */
    public static <T> BaseResponse<T> error(String code, String msg) {
        return new BaseResponse<T>().code(code).msg(msg);
    }

}
