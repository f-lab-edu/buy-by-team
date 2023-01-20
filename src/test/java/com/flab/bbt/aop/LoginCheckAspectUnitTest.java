package com.flab.bbt.aop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.payment.controller.PaymentController;
import com.flab.bbt.payment.request.PaymentRequest;
import com.flab.bbt.payment.service.PaymentService;
import com.flab.bbt.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
@ExtendWith(MockitoExtension.class)
public class LoginCheckAspectUnitTest extends AbstractContainerBaseTest {


    @Test
    @DisplayName("user가 로그인 했을 때 USER_UNAUTHORIZED Exception 발생없이 target이 성공적으로 동작한다.")
    void doNotRaiseExceptionWhenUserSignIn() {

        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(SessionConst.COOKIE_SESSION_ID, buildUser());

        LoginCheckAspect aspect = new LoginCheckAspect(mockHttpSession);
        // when then
        assertDoesNotThrow(() -> aspect.loginCheck());
    }

    @Test
    @DisplayName("user가 로그인을 안했다면 loginCheck Aspect에서 USER_UNAUTHORIZED exception을 발생시킨다.")
    void RaiseExceptionWhenUserNotSignIn() {

        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        LoginCheckAspect aspect = new LoginCheckAspect(mockHttpSession);

        // when then
        CustomException e = assertThrows(CustomException.class, () -> aspect.loginCheck());
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.USER_UNAUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("LoginCheckAspect의 pointcut 정상동작 테스트")
    void pointcutSuccessTest() throws NoSuchMethodException {

        // given
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(LoginCheckAspect.LOGIN_POINT_CUT);
        Method method = TestController.class.getMethod("test", String.class);

        assertThat(pointcut.matches(method, TestController.class)).isTrue();
    }

    @Test
    @DisplayName("Service 컴포넌트는 @LoginCheck 어노테이션이 있어도 LoginCheckAspect 동작안함 ")
    void pointcutServiceFailTest() throws NoSuchMethodException {

        // given
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(LoginCheckAspect.LOGIN_POINT_CUT);
        Method method = TestService.class.getMethod("test", String.class);

        assertThat(pointcut.matches(method, TestService.class)).isFalse();
    }

    private User buildUser() {
        return User.builder()
                .email("test@test.com")
                .password("encryptedPassword")
                .build();
    }
}

@RestController
class TestController {

    @LoginCheck
    public String test(String str) {
        return "test";
    }
}

@RestController
class TestService {

    @LoginCheck
    public String test(String str) {
        return "test";
    }
}
