package com.flab.bbt.common;

import com.flab.bbt.exception.ErrorCode;

public class CommonResponse<T> {
    private boolean isSuccess;
    private T data;
    private Long errorCode;
    private String message;

    public CommonResponse(boolean isSuccess, T data, Long errorCode, String message) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.errorCode = errorCode;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public T getData() {
        return data;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public static <T> CommonResponse success(){
        return new CommonResponse(true, null, null, null);
    }

    public static <T> CommonResponse success(T data){
        return new CommonResponse(true, data, null, null);
    }

    public static CommonResponse fail(ErrorCode errorCode){
        return new CommonResponse(false, null, errorCode.getCode(), errorCode.getMessage());
    }

    public static CommonResponse fail(ErrorCode errorCode, String msg){
        return new CommonResponse(false, null, errorCode.getCode(), msg);
    }
}

