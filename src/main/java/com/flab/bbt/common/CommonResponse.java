package com.flab.bbt.common;

import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

public class CommonResponse<T> {
    private boolean isSuccess;
    private T data;
    private Long errorCode;
    private String message;
    /**
     * validation 에러의 상세 내용을 내려줄 때 사용합니다.
     * 아래와 같은 구조입니다.
     * {
     *     "data": null,
     *     "errorCode": 4000,
     *     "message": "입력값이 올바르지 않습니다.",
     *     "success": false,
     *     "errors": [
     *         {
     *             "field": "password",
     *             "message": "패스워드는 필수 입력 값입니다."
     *         }
     *     ]
     * }
     */
    private List<FieldError> errors;

    // errors의 경우 값이 없을 때에는 [] 빈 배열로 내려줍니다.
    // [Todo] 리팩토링 필요(현재 CommonResponse에 일반 응답과 에러 응답이 섞여있는 구조입니다. 향후 리팩토링이 필요해 보입니다)
    public CommonResponse(boolean isSuccess, T data, Long errorCode, String message, List<FieldError> errors) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
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

    public List<FieldError> getErrors() {
        return errors;
    }

    public static class FieldError {
        private final String field;
        private final String message;

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

        @Builder
        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @Override
        public String toString() {
            return "FieldError{" +
                    "field='" + field + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }

    }

    public static <T> CommonResponse success(){
        return new CommonResponse(true, null, null, null, Collections.emptyList());
    }

    public static <T> CommonResponse success(T data){
        return new CommonResponse(true, data, null, null, Collections.emptyList());
    }

    public static CommonResponse fail(ErrorCode errorCode){
        return new CommonResponse(false, null, errorCode.getCode(), errorCode.getMessage(), Collections.emptyList());
    }

    public static CommonResponse fail(ErrorCode errorCode, List<FieldError> errors){
        return new CommonResponse(false, null, errorCode.getCode(), errorCode.getMessage(), errors);
    }

    public static CommonResponse fail(ErrorCode errorCode, String msg){
        return new CommonResponse(false, null, errorCode.getCode(), msg, Collections.emptyList());
    }
}

