package com.flab.bbt.aop;

import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final HttpSession session;

    @Before("@annotation(com.flab.bbt.aop.LoginCheck)")
    public void loginCheck() {
        User user = (User) session.getAttribute(SessionConst.COOKIE_SESSION_ID);

        if (user == null) {
            throw new CustomException(ErrorCode.USER_UNAUTHORIZED);
        }
    }
}
