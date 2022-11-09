package com.flab.bbt.auth.controller;

import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.response.SignInResponse;
import com.flab.bbt.auth.response.SignUpResponse;
import com.flab.bbt.auth.service.AuthService;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public CommonResponse<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        // authService.signUp(request.email, request.password)
//        return CommonResponse.success(new SignUpResponse());
        return CommonResponse.fail(ErrorCode.UserNotFound);
    }

    @PostMapping("/signin")
    public CommonResponse<SignInResponse> signIn(@RequestBody SignInRequest request){
        // authenticate - username, password
         User user = authService.authenticate(request.getEmail(), request.getPassword()).get();
        // authorize - generate jwt etc.
        // parse response - user detail, jwt
        return CommonResponse.success(new SignInResponse(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getPhoneNo()));
    }

    @PostMapping("/signout")
    public void signOut(){
// remove cookie
    }

}
