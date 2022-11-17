package com.flab.bbt.auth.controller;

import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.response.SignInResponse;
import org.springframework.http.HttpStatus;
import com.flab.bbt.auth.service.AuthService;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.user.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse signUp(@Valid @RequestBody SignUpRequest request){
        // 회원가입 진행
        authService.signUp(request);

        return CommonResponse.success();
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse signIn(@Valid @RequestBody SignInRequest request){
        authService.authenticate(request);

        // authorize - session

        return CommonResponse.success();
    }

    @PostMapping("/signout")
    public void signOut(){
// remove cookie
    }

}
