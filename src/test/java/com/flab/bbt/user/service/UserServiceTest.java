package com.flab.bbt.user.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserInfo;
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
    UserInfo userInfo;

    @BeforeEach
    void setup() {
        user = User.builder()
            .email("test@test.com")
            .encryptedPassword("encryptedPassword")
            .build();

        userInfo = UserInfo.builder()
            .name("updatedName")
            .phoneNo("01012341234")
            .build();
    }

    @Test
    @DisplayName("유저인포 업데이트에 성공한다.")
    void updateSuccessTest() {
        // given
        Long userId = 1L;
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User targetUser = userService.findUserById(userId);

        // when
        userService.update(targetUser, userInfo);

        // then
        assertThat(user.getUserInfo()).isEqualTo(userInfo);
    }
}