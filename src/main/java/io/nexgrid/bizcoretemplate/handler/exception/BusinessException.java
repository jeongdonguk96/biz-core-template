package io.nexgrid.bizcoretemplate.handler.exception;

import io.nexgrid.bizcoretemplate.constant.ResultCode;
import lombok.Getter;

import java.io.Serial;

@Getter
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    // 객체를 구분하는 식별자 (직렬화)

    private ResultCode errorCode;
    private String message;

    public BusinessException(ResultCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
