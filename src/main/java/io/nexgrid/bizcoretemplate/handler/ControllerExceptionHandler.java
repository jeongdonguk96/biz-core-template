package io.nexgrid.bizcoretemplate.handler;

import io.nexgrid.bizcoretemplate.entity.ResultCode;
import io.nexgrid.bizcoretemplate.entity.ResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든컨트롤러 Exception 발생시 이쪽으로 intercept
public class ControllerExceptionHandler {

    // https://velog.io/@kiiiyeon/%EC%8A%A4%ED%94%84%EB%A7%81-ExceptionHandler%EB%A5%BC-%ED%86%B5%ED%95%9C-%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC

    /**
     * // @Valid, @Validated 애너테이션에 의해 발생한 예외 핸들러
     *
     * @param ex MethodArgumentNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultDto<Object>> validationException(MethodArgumentNotValidException ex) {

        ResultCode errorCode = ResultCode.INVALID_PARAMETER;
        // CustomException이 아니라 핸들러에서 객체선언

        // TODO 에러 uri 찍을지 생각
        BindingResult bindingResult = ex.getBindingResult();

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Exception 발생 : "+ex.toString()+", "+ex.getMessage());
        System.out.println("ErrorCode : "+errorCode.getCode()+", "+errorCode.getMessage());
        for (FieldError error : bindingResult.getFieldErrors()) {
            // 검증 실패한 field, msg, input값 출력
            System.out.println("### Validation Error : [Field:"+error.getField()+", Msg:"+error.getDefaultMessage()+", InputValue:"+error.getRejectedValue()+"]");
        }
        System.out.println("----------------------------------------------------------------------------------------------------");

        return ResponseEntity.ok(ResultDto.resultSet(errorCode.getCode()
                                                    , errorCode.getMessage()));
    }



    // TODO BusinessException 핸들러 추가 해야함
//    System.out.println("----------------------------------------------------------------------------------------------------");
//    System.out.println("Exception 발생 : "+ex.toString()+", "+ex.getMessage());
//    System.out.println("ErrorCode : "+errorCode.getCode()+", "+errorCode.getMessage());
//    System.out.println(ex.getStackTrace()[0]+"\n\t"+ex.getStackTrace()[1]+"\n\t"+ex.getStackTrace()[2]+"\n\t"+ex.getStackTrace()[3]+"\n\t"+ex.getStackTrace()[4]); // 5줄까지만 찍기
//    System.out.println("----------------------------------------------------------------------------------------------------");




    /**
     * 모든 예외에 대한 핸들러
     *
     * @param ex Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResultDto<Object>> handleException(Exception ex) {
        ResultCode errorCode = ResultCode.FAIL;
        return ResponseEntity.ok(ResultDto.resultSet(errorCode.getCode()
                                                    , errorCode.getMessage()));
    }


}
