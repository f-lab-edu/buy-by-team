package com.flab.bbt.auth.controller;

import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.service.AuthService;
import com.flab.bbt.auth.service.PasswordEncrypter;
import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.LoggerUtils;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.response.UserSignUpResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerUtils.getLogger(AuthController.class);

    private final AuthService authService;
    private final PasswordEncrypter passwordEncrypter;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        // 회원가입 진행
        User user = authService.signUp(signUpRequest.convertToEntityWith(
            passwordEncrypter.encrypt(signUpRequest.getPassword())));

        return CommonResponse.success(UserSignUpResponse.convertToUserSignUpResponse(user));
    }

    //logger.info("debug message", user);
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse signIn(@Valid @RequestBody SignInRequest signInRequest,
        HttpServletRequest request) {
        logger.info("Attempting to sign in user with email: {}", signInRequest.getEmail());

        // authenticate user
        User user = signInRequest.convertToEntityWith(
            passwordEncrypter.encrypt(signInRequest.getPassword()));

        Long userId = authService.authenticate(user);

        if (userId == null) {
            logger.info("User not found with email: {}", signInRequest.getEmail());
            return CommonResponse.fail(ErrorCode.USER_NOT_FOUND);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.COOKIE_SESSION_ID, userId);
            logger.info("User with email {} successfully signed in with user ID {}",
                signInRequest.getEmail(), userId);
            return CommonResponse.success();
        }
    }

    @PostMapping("/signout")
    public void signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
