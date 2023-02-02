package com.flab.bbt.exception;

public enum ErrorCode {
    // User/Auth
    USER_NOT_FOUND(3000, "유저를 찾지 못했습니다."),
    INVALID_INPUT(3001, "입력값이 올바르지 않습니다."),
    DUPLICATE_EMAIL(3002, "이메일이 중복되었습니다."),
    USER_NOT_AUTHORIZED(3003, "권한이 없는 유저입니다."),
    USER_UNAUTHORIZED(3004, "로그인이 필요합니다."),

    USE_RPROFILE_NOT_FOUNT(4000, "유저프로필을 찾지 못했습니다."),
    USER_PROFILE_ALREADY_EXISTS(4001, "유저프로필이 이미 존재합니다."),

    // Product
    PRODUCT_NOT_FOUND(5000, "상품을 찾지 못했습니다."),
    PRODUCT_ALREADY_EXISTS(5001, "이미 존재하는 상품입니다."),

    // Payment
    PAYMENT_NOT_FOUND(6000, "존재하지 않은 결제 정보입니다."),

    // Deal
    DEAL_NOT_FOUND(7000, "딜이 존재하지 않습니다."),

    DEAL_GROUP_SIZE_EXCEEDED(7001, "딜의 정원을 초과했습니다."),
    CONCURRENT_ACCESS(7002, "동시 접근이 발생했습니다."),

    // PRICE_TABLE
    PRICE_TABLE_NOT_FOUND(7000, "해당 제품에 대한 가격/팀구매 정보가 존재하지 않습니다.");

    long code;
    String message;

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
