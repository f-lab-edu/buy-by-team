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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
@ExtendWith(MockitoExtension.class)
public class LoginCheckAspectUnitTest extends AbstractContainerBaseTest {

    private PaymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    MockHttpSession mockHttpSession;

    @BeforeEach
    void beforeEach() {
        mockHttpSession = new MockHttpSession();
        final PaymentController target = new PaymentController(paymentService);

        final AspectJProxyFactory proxyFactory = new AspectJProxyFactory(target);
        proxyFactory.addAspect(new LoginCheckAspect(mockHttpSession));

        paymentController = proxyFactory.getProxy();
    }

    @Test
    @DisplayName("user가 로그인 했을 때 USER_UNAUTHORIZED Exception 발생없이 target이 성공적으로 동작한다.")
    void doNotRaiseExceptionWhenUserSignIn() {
        // given
        mockHttpSession.setAttribute(SessionConst.COOKIE_SESSION_ID, buildUser());

        // when then
        assertDoesNotThrow(() -> paymentController.createPayment(buildPaymentRequest(), mockHttpSession));
    }

    @Test
    @DisplayName("user가 로그인을 안했다면 loginCheck Aspect에서 USER_UNAUTHORIZED exception을 발생시킨다.")
    void RaiseExceptionWhenUserNotSignIn() {
        // when
        CustomException e = assertThrows(CustomException.class,
            () -> paymentController.createPayment(buildPaymentRequest(), mockHttpSession));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.USER_UNAUTHORIZED.getMessage());
    }

    private PaymentRequest buildPaymentRequest() {
        return PaymentRequest.builder()
            .userId(1L)
            .orderId(1L)
            .method(1)
            .build();
    }

    private User buildUser() {
        return User.builder()
            .email("test@test.com")
            .password("encryptedPassword")
            .build();
    }
}
