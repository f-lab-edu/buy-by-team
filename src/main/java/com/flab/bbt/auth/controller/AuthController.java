package com.flab.bbt.auth.controller;

import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.service.AuthService;
import com.flab.bbt.auth.service.PasswordEncrypter;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncrypter passwordEncrypter;

    public AuthController(AuthService authService, PasswordEncrypter passwordEncrypter) {
        this.authService = authService;
        this.passwordEncrypter = passwordEncrypter;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        // 회원가입 진행
        signUpRequest.setPassword(passwordEncrypter.encrypt(signUpRequest.getPassword()));
        User user = signUpRequest.convertToEntity();
        authService.signUp(user);

        return CommonResponse.success();
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse signIn(@Valid @RequestBody SignInRequest signInRequest,
        HttpServletRequest request) {
        // authenticate user
        signInRequest.setPassword(passwordEncrypter.encrypt(signInRequest.getPassword()));
        User user = signInRequest.convertToEntity();
        User authenticatedUser = authService.authenticate(user);

        // authorize user via session
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.COOKIE_SESSION_ID, authenticatedUser);
        return CommonResponse.success();
    }

    @PostMapping("/signout")
    public void signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
