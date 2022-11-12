package com.flab.bbt.auth.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignInRequest {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotEmpty
    private String password;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

}
