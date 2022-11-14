package com.flab.bbt.common;

import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.flab.bbt.exception.ErrorCode.UserNotFound;

@ControllerAdvice
public class BBTExceptionHandler {
    @ExceptionHandler(value= {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotExistException(UserNotFoundException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CommonResponse bbtException = CommonResponse.fail(UserNotFound, "User does not exist");
        return new ResponseEntity<>(bbtException, httpStatus);
    }

}
