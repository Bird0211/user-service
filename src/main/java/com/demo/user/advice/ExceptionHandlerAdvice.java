package com.demo.user.advice;

import com.demo.user.aop.BaseResponse;
import com.demo.user.enums.StatusCode;
import com.demo.user.exception.CustomException;
import com.demo.user.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * Exception
     *
     * @param e
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new Result<>(StatusCode.SYS_ERROR.getCode(), StatusCode.SYS_ERROR.getCodeMsg(), null);
    }

    /**
     * process RuntimeException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new Result<>(StatusCode.SYS_ERROR.getCode(), StatusCode.SYS_ERROR.getCodeMsg(), null);
    }

    /**
     * Process BaseException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result<Void> handleCustomException(CustomException e) {
        StatusCode code = e.getStatusCode();
        String errorMsg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : code.getCodeMsg();
        log.info("Error: {} {}", code.getCode(), errorMsg);
        return new Result<>(code.getCode(), errorMsg, null);
    }


}
