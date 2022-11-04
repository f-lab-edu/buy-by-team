package com.flab.bbt.exception;

public enum ErrorCode {
    UserNotFound(3000, "abc");

    long code;
    String message;

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(long code, String msg){

    }
}
