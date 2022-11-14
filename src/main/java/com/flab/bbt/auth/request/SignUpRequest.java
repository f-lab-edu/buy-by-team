package com.flab.bbt.auth.request;

import com.flab.bbt.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpRequest {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    private String password;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String phoneNo;

    public User convertToEntity(SignUpRequest request) {
        return User.builder()
                   .email(request.getEmail())
                   .password(request.getPassword())
                   .name(request.getName())
                   .phoneNo(request.getPhoneNo())
                   .build();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
