package com.flab.bbt.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.request.UpdateUserRequest;
import com.flab.bbt.user.request.UserProfileRequest;
import com.flab.bbt.user.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class UserControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("userProfile이 생성되면 200 ok를 내려준다.")
    void createUserProfileSuccessTest() throws Exception {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(SessionConst.COOKIE_SESSION_ID, getUser());

        when(userService.createUserProfile(any(User.class))).thenReturn(getUser());

        String content = objectMapper.writeValueAsString(getUserProfileRequest());

        // when
        mockMvc.perform(post("/users/1/user-profiles")
                .content(content)
                .session(mockHttpSession)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("userProfile을 업데이트 후 200 ok를 내려준다.")
    void updateUserProfiileSuccessTest() throws Exception {
        // given
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(SessionConst.COOKIE_SESSION_ID, getUser());

        when(userService.updateUserProfile(any(User.class), any(UserProfile.class))).thenReturn(getUser());

        String content = objectMapper.writeValueAsString(getUpdateUserRequest());

        // when
        mockMvc.perform(patch("/user-profiles")
                .content(content)
                .session(mockHttpSession)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            // then
            .andExpect(status().isOk());
    }

    private User getUser() {
        return User.builder()
            .id(1L)
            .email("test@test.com")
            .password("encryptedPassword")
            .build();
    }

    private UserProfile getUserProfile() {
        return UserProfile.builder()
            .id(1L)
            .name("test")
            .phoneNo("01012341234")
            .userId(getUser().getId())
            .build();
    }

    private UserProfileRequest getUserProfileRequest() {
        return UserProfileRequest.builder()
            .name("test")
            .phoneNo("01012341234")
            .build();
    }

    private UpdateUserRequest getUpdateUserRequest() {
        return UpdateUserRequest.builder()
            .name("updatedName")
            .phoneNo("01043214321")
            .build();
    }
}