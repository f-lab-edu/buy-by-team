package com.flab.bbt.auth.controller;

import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.service.AuthService;
import com.flab.bbt.auth.service.PasswordEncrypter;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionManager;
import com.flab.bbt.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final PasswordEncrypter passwordEncrypter;

    private final SessionManager sessionManager;

    public AuthController(AuthService authService, PasswordEncrypter passwordEncrypter, SessionManager sessionManager) {
        this.authService = authService;
        this.passwordEncrypter = passwordEncrypter;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse signUp(@Valid @RequestBody SignUpRequest request){
        // 회원가입 진행
        User user = request.convertToEntity(request);
        user.setEncryptedPassword(passwordEncrypter.encrypt(request.getPassword()));

        authService.signUp(user);

        return CommonResponse.success();
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse signIn(@Valid @RequestBody SignInRequest request, HttpServletResponse response){
        User user = request.convertToEntity(request);
        user.setEncryptedPassword(passwordEncrypter.encrypt(request.getPassword()));
        User authenticatedUser = authService.authenticate(user);

        // authorize - session
        sessionManager.createSession(authenticatedUser, response);
        return CommonResponse.success();
    }

    @PostMapping("/signout")
    public void signOut(HttpServletRequest request){
        // remove cookie
        sessionManager.expire(request);
    }

}
