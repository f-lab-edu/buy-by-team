package com.flab.bbt.aop;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.payment.request.PaymentRequest;
import com.flab.bbt.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class LoginCheckAspectTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
            .email("test@test.com")
            .password("encryptedPassword")
            .build();
    }

    @Test
    @DisplayName("로그인 했을 시 @LoginCheck가 적용된 메소드가 성공적으로 동작한다.")
    void loginCheckSuccessTest() throws Exception {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(SessionConst.COOKIE_SESSION_ID, user);

        String content = objectMapper.writeValueAsString(new PaymentRequest(user.getId(), 1L, 1));

        // when
        mockMvc.perform(post("/payment/create")
                .content(content)
                .session(mockHttpSession)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인이 안되어있을 시 USER_UNAUTHORIZED의 CustomException을 발생시킨다.")
    void loginCheckFailTest() throws Exception {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();

        String content = objectMapper.writeValueAsString(new PaymentRequest(user.getId(), 1L, 1));

        // when
        mockMvc.perform(post("/payment/create")
                .content(content)
                .session(mockHttpSession)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(result -> assertTrue(
                result.getResolvedException().getClass().isAssignableFrom(CustomException.class)
            ))
            .andExpect(jsonPath("message", is(ErrorCode.USER_UNAUTHORIZED.getMessage())))
            .andExpect(status().isBadRequest());

    }

}