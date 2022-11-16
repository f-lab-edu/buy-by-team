package com.flab.bbt.exception;

public enum ErrorCode {
    USER_NOT_FOUND(3000, "유저를 찾지 못했습니다."),
    INVALID_INPUT(4000, "필수 입력값이 누락되었습니다.");

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
