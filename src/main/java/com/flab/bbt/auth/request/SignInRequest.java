package com.flab.bbt.auth.request;

import com.flab.bbt.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignInRequest {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    private String password;

    public User convertToEntity() {
        return User.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .build();
    }
    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

}
