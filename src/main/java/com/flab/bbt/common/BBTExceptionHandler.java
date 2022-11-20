package com.flab.bbt.common;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BBTExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public CommonResponse handleCustomException(CustomException e){
        return CommonResponse.fail(e.getErrorCode());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<CommonResponse.FieldError> errors = buildFieldError(e.getBindingResult());

        return CommonResponse.fail(ErrorCode.INVALID_INPUT, errors);
    }

    private List<CommonResponse.FieldError> buildFieldError(BindingResult result) {
        return result.getFieldErrors().stream()
                               .map(error -> CommonResponse.FieldError.builder()
                                       .field(error.getField())
                                       .message(error.getDefaultMessage())
                                       .build())
                               .collect(Collectors.toList());
    }
}
