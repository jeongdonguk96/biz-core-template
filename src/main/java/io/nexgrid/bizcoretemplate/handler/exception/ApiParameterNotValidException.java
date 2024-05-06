package io.nexgrid.bizcoretemplate.handler.exception;

import io.nexgrid.bizcoretemplate.constant.ResultCode;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.io.Serial;

@Getter
public class ApiParameterNotValidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    // 객체를 구분하는 식별자 (직렬화)

    private ResultCode resultCode;
    private BindingResult bindingResult;

    public ApiParameterNotValidException(BindingResult bindingResult) {
        super(ResultCode.NOT_VALID_PARAMETER.getMessage());
        this.resultCode = ResultCode.NOT_VALID_PARAMETER;
        this.bindingResult = bindingResult;
    }

}
