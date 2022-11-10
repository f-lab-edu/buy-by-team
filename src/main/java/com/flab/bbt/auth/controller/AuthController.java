package com.flab.bbt.auth.controller;

import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.response.SignInResponse;
import com.flab.bbt.auth.response.SignUpResponse;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.exception.ErrorCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signup")
    public CommonResponse<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        // authService.signUp(request.email, request.password)
//        return CommonResponse.success(new SignUpResponse());
        return CommonResponse.fail(ErrorCode.UserNotFound);
    }

    @PostMapping("/signin")
    public CommonResponse<SignInResponse> signIn(){
        // authService.signIn(request.email, request.password)
        return CommonResponse.success(new SignInResponse());
    }

    @PostMapping("/signout")
    public void signOut(){
// remove cookie
    }

}
