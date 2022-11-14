package com.flab.bbt.common;

import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.flab.bbt.exception.ErrorCode.INVALID_INPUT;
import static com.flab.bbt.exception.ErrorCode.UserNotFound;

@ControllerAdvice
public class BBTExceptionHandler {
    @ExceptionHandler(value= {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotExistException(UserNotFoundException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CommonResponse bbtException = CommonResponse.fail(UserNotFound, "User does not exist");
        return new ResponseEntity<>(bbtException, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        CommonResponse response = CommonResponse.fail(INVALID_INPUT, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
