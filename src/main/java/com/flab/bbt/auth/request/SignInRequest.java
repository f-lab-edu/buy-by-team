package com.flab.bbt.auth.request;

public class SignInRequest {
    // TODO("Add validation constraints")
    private String email;
    private String password;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

}
