package com.flab.bbt.user.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.UserRepository;
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

    User user;
    UserProfile userProfile;

    @BeforeEach
    void setup() {
        user = User.builder()
            .email("test@test.com")
            .encryptedPassword("encryptedPassword")
            .build();

        userProfile = UserProfile.builder()
            .name("updatedName")
            .phoneNo("01012341234")
            .build();
    }

    @Test
    @DisplayName("유저프로필 업데이트에 성공한다.")
    void updateSuccessTest() {
        // given
        when(userRepository.update(any(User.class))).thenReturn(user);

        // when
        userService.update(user, userProfile);

        // then
        verify(userRepository).update(user);
    }
}