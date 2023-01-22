package com.flab.bbt.aop;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.payment.controller.PaymentController;
import com.flab.bbt.payment.request.PaymentRequest;
import com.flab.bbt.payment.service.PaymentService;
import com.flab.bbt.user.domain.User;
import java.lang.reflect.Method;
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

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
@ExtendWith(MockitoExtension.class)
public class LoginCheckAspectUnitTest extends AbstractContainerBaseTest {

    @Test
    @DisplayName("user가 로그인한 상태라면 USER_UNAUTHORIZED Exception 발생없이 loginCheck가 성공적으로 진행된다.")
    void loginCheckAspectSuccessTest() {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(SessionConst.COOKIE_SESSION_ID, buildUser());

        LoginCheckAspect aspect = new LoginCheckAspect(mockHttpSession);

        // when then
        assertDoesNotThrow(() -> aspect.loginCheck());
    }

    @Test
    @DisplayName("user가 로그인을 안했다면 loginCheck Aspect에서 USER_UNAUTHORIZED exception을 발생시킨다.")
    void loginCheckFailAndRaiseExceptionTest() {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        LoginCheckAspect aspect = new LoginCheckAspect(mockHttpSession);

        // when
        CustomException e = assertThrows(CustomException.class,
            () -> aspect.loginCheck());

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo(
            ErrorCode.USER_UNAUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("유저 로그인 시 aspect가 적용된 target class의 메서드가 정상적으로 작동한다.")
    void TargetClassWithAspectSuccessTest() {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(SessionConst.COOKIE_SESSION_ID, buildUser());

        TestController proxyController = addAspectAndGetProxy(mockHttpSession);

        // when then
        assertThat(proxyController.test("test")).isEqualTo("test");
        assertDoesNotThrow(() -> proxyController.test("test"));
    }

    @Test
    @DisplayName("유저 로그인 안했을 시 aspect가 적용된 target class의 메서드가 진행되지 않고"
        + "USER_UNAUTHORIZED exception을 발생시킨다.")
    void TargetClassWithAspectFailAndRaiseExceptionTest() {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        TestController proxyController = addAspectAndGetProxy(mockHttpSession);

        // when
        CustomException e = assertThrows(CustomException.class,
            () -> proxyController.test("test"));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo(
            ErrorCode.USER_UNAUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("XXX_Controller 이름이 붙은 클래스의 메소드에 @LoginCheck 어노테이션이 붙어있다면 포인트컷이 정상적으로 적용된다.")
    void pointcutSuccessTest() throws Exception {
        // given
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AopConst.LOGIN_POINTCUT);
        Method method = TestController.class.getMethod("test", String.class);

        // when then
        assertThat(pointcut.matches(method, TestController.class)).isTrue();
    }

    @Test
    @DisplayName("클래스 이름이 XXX_Controller로 끝나지 않는다면 @LoginCheck 어노테이션이 붙어더라도 포인트컷 적용안된다.")
    void pointcutFailTest() throws Exception {
        // given
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AopConst.LOGIN_POINTCUT);
        Method method = TestService.class.getMethod("test", String.class);

        // when then
        assertThat(pointcut.matches(method, TestService.class)).isFalse();
    }

    private User buildUser() {
        return User.builder()
            .email("test@test.com")
            .password("encryptedPassword")
            .build();
    }

    private TestController addAspectAndGetProxy(MockHttpSession mockHttpSession) {
        final TestController target = new TestController();
        final AspectJProxyFactory proxyFactory = new AspectJProxyFactory(target);

        proxyFactory.addAspect(new LoginCheckAspect(mockHttpSession));
        return proxyFactory.getProxy();
    }
}

@RestController
class TestController {

    @LoginCheck
    public String test(String str) {
        return str;
    }
}

@RestController
class TestService {

    @LoginCheck
    public String test(String str) {
        return str;
    }
}