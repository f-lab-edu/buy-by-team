package com.flab.bbt.home.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    @GetMapping("/")
    public CommonResponse authorizeUserBySession(
        @SessionAttribute(name = SessionConst.COOKIE_SESSION_ID, required = false) User user) {
        if (user == null) {
            // authorized user does not exist! redirect to login view
            throw new CustomException(ErrorCode.USER_NOT_AUTHORIZED);
        } else {
            // authorized user exists!
            return CommonResponse.success();
        }
    }
}
