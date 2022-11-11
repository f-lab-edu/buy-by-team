package com.flab.bbt.auth.controller;

import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.response.SignInResponse;
import com.flab.bbt.auth.service.AuthService;
import com.flab.bbt.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse signUp(@RequestBody SignUpRequest request){
        // [Todo] null 유효성검증

        // 회원가입 진행
        authService.signUp(request);

        return CommonResponse.success();
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
