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

import javax.validation.Valid;
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
    public CommonResponse<SignInResponse> signIn(@Valid @RequestBody SignInRequest request){
        // authenticate - username, password
         User user = authService.authenticate(request.getEmail(), request.getPassword()).get();
        // authorize - session

        return CommonResponse.success(
            SignInResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNo(user.getPhoneNo())
                .build()
        );
    }

    @PostMapping("/signout")
    public void signOut(){
// remove cookie
    }

}
