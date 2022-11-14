package com.flab.bbt.exception;

public enum ErrorCode {
    UserNotFound(3000, "abc"),
    INVALID_INPUT(4000, "abc");

    long code;
    String message;

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(long code, String message){
        this.code = code;
        this.message = message;
    }
}
