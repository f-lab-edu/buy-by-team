package com.flab.bbt.auth.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignInRequest {
    @NotEmpty
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
