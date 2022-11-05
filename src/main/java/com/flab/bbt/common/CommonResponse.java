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

    public static <T> CommonResponse success(T o){
        return new CommonResponse(true, o, null, null);
    }

    public static CommonResponse fail(ErrorCode errorCode){
        return new CommonResponse(false, null, errorCode.getCode(), errorCode.getMessage());
    }

    public static CommonResponse fail(ErrorCode errorCode, String msg){
        return new CommonResponse(false, null, errorCode.getCode(), msg);
    }
}

