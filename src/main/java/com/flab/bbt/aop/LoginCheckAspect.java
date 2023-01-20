package com.flab.bbt.aop;

import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class LoginCheckAspect {

    public static final String LOGIN_POINT_CUT = "@annotation(com.flab.bbt.aop.LoginCheck) "
            + "&& execution(* com.flab.bbt..*Controller.*(..))";
    @Pointcut(LOGIN_POINT_CUT)
    public void before() {}

    private final HttpSession session;

    @Before("before()")
    public void loginCheck() {
        if (session.getAttribute(SessionConst.COOKIE_SESSION_ID) == null) {
            throw new CustomException(ErrorCode.USER_UNAUTHORIZED);
        }
    }
}
