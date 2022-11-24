package com.flab.bbt.auth.response;

import lombok.Builder;

public class SignInResponse {

    private Long id;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    private String email;
    private String password;
    private String name;
    private String phoneNo;

    @Builder
    public SignInResponse(String email, String password, String name, String phoneNo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}
