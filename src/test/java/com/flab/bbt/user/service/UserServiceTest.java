package com.flab.bbt.user.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.UserRepository;
import com.flab.bbt.user.request.UpdateUserRequest;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("유저프로필 업데이트에 성공한다.")
    void updateSuccessTest() {
        // given
        when(userRepository.findUserProfileByUserId(anyLong())).thenReturn(
            Optional.ofNullable(getUser().getUserProfile()));

        UserProfile updateUserProfile = updateUserRequest().convertToUserProfile();

        // when
        userService.updateUserProfile(getUser(), updateUserProfile);

        // then
        verify(userRepository).updateUserProfile(updateUserProfile);
    }

    private User getUser() {
        return User.builder()
            .id(1L)
            .email("test@test.com")
            .password("encryptedPassword")
            .userProfile(new UserProfile("testName"))
            .build();
    }

    private UpdateUserRequest updateUserRequest() {
        return UpdateUserRequest.builder()
            .name("updatedName")
            .phoneNo("01011111111")
            .build();
    }
}