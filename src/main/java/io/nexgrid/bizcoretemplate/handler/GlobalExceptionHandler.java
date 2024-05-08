package io.nexgrid.bizcoretemplate.handler;

import io.nexgrid.bizcoretemplate.constant.ResultCode;
import io.nexgrid.bizcoretemplate.dto.ResponseDto;
import io.nexgrid.bizcoretemplate.handler.exception.ApiParameterNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)  // 모든 Rest컨트롤러 Exception 발생시 이쪽으로 intercept
public class GlobalExceptionHandler {

    /**
     * . @Valid, @Validated 애너테이션에 의해 발생한 예외 핸들러
     *
     * @param ex ApiParameterNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler(ApiParameterNotValidException.class)
    public ResponseEntity<ResponseDto<Object>> validationException(ApiParameterNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();

        log.error("----------------------------------------------------------------------------------------------------");
        log.error("Exception 발생 ▶ {}", ex.toString());
        log.error("ErrorCode : {}, {}", ex.getResultCode().getCode(), ex.getMessage());
        for (FieldError error : bindingResult.getFieldErrors()) {
            // 검증 실패한 field, msg, input값 출력
            log.error("### Validation Error : [Field:{}, Msg:{}, InputValue:{}]", error.getField(), error.getDefaultMessage(), error.getRejectedValue());
        }
        log.error("----------------------------------------------------------------------------------------------------");

        return ResponseEntity.badRequest().body(ResponseDto.resultSet(ex.getResultCode().getCode()
                                                                    , ex.getMessage()));
    }

    /**
     * 모든 예외에 대한 핸들러
     *
     * @param ex Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseDto<Object>> handleException(Exception ex) {
        ResultCode errorCode = ResultCode.FAIL;

        log.error("----------------------------------------------------------------------------------------------------");
        log.error("Exception 발생 ▶ {}", ex.toString());
        log.error("ErrorCode : {}, {}", errorCode.getCode(), errorCode.getMessage());
        for (int i=0; i<5; i++){
            // 5줄까지만 출력
            log.error("\t\t"+ex.getStackTrace()[i].toString());
        }
        log.error("----------------------------------------------------------------------------------------------------");

        return ResponseEntity.ok(ResponseDto.resultSet(errorCode.getCode()
                                                    , errorCode.getMessage()));
    }


    /**
     * 잘못된 형식으로 요청시
     *
     * @param ex MethodArgumentTypeMismatchException
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto<Object>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ResultCode errorCode = ResultCode.INVALID_REQUEST_FORMAT;

        log.error("----------------------------------------------------------------------------------------------------");
        log.error("Exception 발생 ▶ {}", ex.toString());
        log.error("ErrorCode : {}, {}", errorCode.getCode(), errorCode.getMessage());
        for (int i=0; i<5; i++){
            log.error("\t\t"+ex.getStackTrace()[i].toString());
        }
        log.error("----------------------------------------------------------------------------------------------------");

        return ResponseEntity.badRequest().body(ResponseDto.resultSet(errorCode.getCode()
                                                                    , errorCode.getMessage()));
    }
}
