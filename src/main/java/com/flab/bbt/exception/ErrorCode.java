package com.flab.bbt.exception;

public enum ErrorCode {
    // User/Auth
    USER_NOT_FOUND(3000, "유저를 찾지 못했습니다."),
    INVALID_INPUT(3001, "입력값이 올바르지 않습니다."),
    DUPLICATE_EMAIL(3002, "이메일이 중복되었습니다."),
    USER_NOT_AUTHORIZED(3003, "권한이 없는 유저입니다."),

    // Product
    PRODUCT_NOT_FOUND(5000, "상품을 찾지 못했습니다."),
    PRODUCT_ALREADY_EXISTS(5001, "이미 존재하는 상품입니다.");

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
