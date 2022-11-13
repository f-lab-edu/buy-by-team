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
    public CommonResponse signUp(@RequestBody SignUpRequest request){
        // [Todo] null 유효성검증

        // 회원가입 진행
        User user = request.convertToEntity(request);
        authService.signUp(user);

        return CommonResponse.success();
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
