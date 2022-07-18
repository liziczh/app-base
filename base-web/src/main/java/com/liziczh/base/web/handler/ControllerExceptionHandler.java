package com.liziczh.base.web.handler;

import com.liziczh.base.api.common.response.BaseResponse;
import com.liziczh.base.common.enums.BaseCodeEnum;
import com.liziczh.base.common.exception.BizErrorException;
import com.liziczh.base.common.exception.BizInfoException;
import com.liziczh.base.common.exception.BizWarnException;
import com.liziczh.base.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller异常转换
 *
 * @author chenzhehao
 * @version 1.0
 * @description
 * @date 2022/1/16 12:50 下午
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BizInfoException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse<Void> handleBizInfoException(BizInfoException e) {
        log.info("ControllerExceptionHandler.handleBizInfoException, code={}, msg={}", e.getCode(), e.getMessage());
        return buildResponse(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BizWarnException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse<Void> handleBizWarnException(BizWarnException e) {
        log.warn("ControllerExceptionHandler.handleBizWarnException, code={}, msg={}", e.getCode(), e.getMessage());
        return buildResponse(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BizErrorException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse<Void> handleBizErrorException(BizErrorException e) {
        log.error("ControllerExceptionHandler.handleBizErrorException, code={}, msg={}", e.getCode(), e.getMessage());
        return buildResponse(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public BaseResponse<Void> handleException(Exception e) {
        log.error("ControllerExceptionHandler.handleException, e", e);
        return buildResponse(null, null);
    }

    /**
     * 构建异常错误响应信息
     *
     * @param code
     * @param msg
     * @return com.liziczh.mvc.mesh.api.common.response.BaseResponse<java.lang.Void>
     * @author chenzhehao
     * @date 2022/1/16 10:10 下午
     */
    private BaseResponse<Void> buildResponse(String code, String msg) {
        BaseResponse<Void> baseResponse = BaseResponse.<Void>builder()
                .code(StringUtils.isBlank(code) ? BaseCodeEnum.ERROR.getCode() : code)
                .msg(StringUtils.isBlank(msg) ? BaseCodeEnum.ERROR.getDesc() : msg)
                .build();
        log.info("ControllerExceptionHandler.buildResponse, response={}", JsonUtils.toJson(baseResponse));
        return baseResponse;
    }
}
