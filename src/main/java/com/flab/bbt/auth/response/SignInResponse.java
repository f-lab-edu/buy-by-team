package com.flab.bbt.auth.response;

public class SignInResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNo;

    public SignInResponse(Long id, String email, String password, String name, String phoneNo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}
