package io.nexgrid.bizcoretemplate.handler;

import io.nexgrid.bizcoretemplate.constant.ResultCode;
import io.nexgrid.bizcoretemplate.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 모든Rest컨트롤러 Exception 발생시 이쪽으로 intercept
public class ControllerExceptionHandler {

    // https://velog.io/@kiiiyeon/%EC%8A%A4%ED%94%84%EB%A7%81-ExceptionHandler%EB%A5%BC-%ED%86%B5%ED%95%9C-%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC

    /**
     * // @Valid, @Validated 애너테이션에 의해 발생한 예외 핸들러
     *
     * @param ex MethodArgumentNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Object>> validationException(MethodArgumentNotValidException ex) {

        ResultCode errorCode = ResultCode.INVALID_PARAMETER;
        // CustomException이 아니라 핸들러에서 객체선언

        // TODO 에러 uri 찍을지 생각
        BindingResult bindingResult = ex.getBindingResult();

        log.error("----------------------------------------------------------------------------------------------------");
        log.error("Exception 발생 : {}, {}", ex.toString(), ex.getMessage());
        log.error("ErrorCode : {}, {}", errorCode.getCode(), errorCode.getMessage());
        for (FieldError error : bindingResult.getFieldErrors()) {
            // 검증 실패한 field, msg, input값 출력
            log.error("### Validation Error : [Field:{}, Msg:{}, InputValue:{}]", error.getField(), error.getDefaultMessage(), error.getRejectedValue());
        }
        log.error("----------------------------------------------------------------------------------------------------");

        return ResponseEntity.ok(ResponseDto.resultSet(errorCode.getCode()
                                                    , errorCode.getMessage()));
    }



    // TODO BusinessException 핸들러 추가 해야함



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
        log.error("Exception 발생 : {}, {}", ex.toString(), ex.getMessage());
        log.error("ErrorCode : {}, {}", errorCode.getCode(), errorCode.getMessage());
        log.error(ex.getStackTrace()[0]+"\n\t"+ex.getStackTrace()[1]+"\n\t"+ex.getStackTrace()[2]+"\n\t"+ex.getStackTrace()[3]+"\n\t"+ex.getStackTrace()[4]); // 5줄까지만 출력
        log.error("----------------------------------------------------------------------------------------------------");

        return ResponseEntity.ok(ResponseDto.resultSet(errorCode.getCode()
                                                    , errorCode.getMessage()));
    }


}
