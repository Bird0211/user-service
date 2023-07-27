package com.demo.user.advice;

import com.demo.user.aop.BaseResponse;
import com.demo.user.enums.StatusCode;
import com.demo.user.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = BaseResponse.class)
@Slf4j
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        log.info("returnType:" + returnType);
        log.info("converterType:" + converterType);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (MediaType.APPLICATION_JSON.equals(selectedContentType)) {
            if (body instanceof Result) {
                return body;
            } else {
                Result<Object> responseResult = new Result<>(StatusCode.SUCCESS.getCode(),
                        StatusCode.SUCCESS.getCodeMsg(),
                        body);
                return responseResult;
            }
        }
        return body;
    }
}
