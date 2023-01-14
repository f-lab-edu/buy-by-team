package com.flab.bbt.auth.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.auth.service.AuthService;
import com.flab.bbt.auth.service.PasswordEncrypter;
import com.flab.bbt.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class AuthControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncrypter passwordEncrypter;

    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("회원가입 성공시 201 created를 내려준다.")
    void signUpSuccessTest() throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
            .email("test@test.com")
            .password("password")
            .build();

        String content = objectMapper.writeValueAsString(signUpRequest);

        // when
        mockMvc.perform(post("/auth/signup")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("로그인 성공시 200 ok를 내려준다.")
    void signInSuccessTest() throws Exception {
        // given
        SignInRequest signInRequest = SignInRequest.builder()
            .email("test@test.com")
            .password("password")
            .build();

        testUserSignUp();

        String content = objectMapper.writeValueAsString(signInRequest);
        MockHttpSession mockHttpSession = new MockHttpSession();

        // when
        mockMvc.perform(post("/auth/signin")
                .session(mockHttpSession)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    private void testUserSignUp() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
            .email("test2@test.com")
            .password("password")
            .build();

        User user = signUpRequest.convertToEntityWith(
            passwordEncrypter.encrypt(signUpRequest.getPassword()));

        authService.signUp(user);
    }
}