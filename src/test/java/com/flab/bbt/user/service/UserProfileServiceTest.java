package com.flab.bbt.user.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.UserProfileRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceTest {

    @InjectMocks
    private UserProfileService userProfileService;

    @Mock
    private UserProfileRepository userProfileRepository;

    UserProfile userProfile;
    UserProfile updatableUserProfile;

    @BeforeEach
    void setup() {
        userProfile = UserProfile.builder()
            .name("name")
            .phoneNo("01011111111")
            .build();
    }

    @Test
    @DisplayName("유저프로필 업데이트에 성공한다.")
    void updateSuccessTest() {
        // given
        when(userProfileRepository.findByUserId(anyLong())).thenReturn(
            Optional.ofNullable(userProfile));

        // when
        Long userId = 1L;
        userProfileService.update(userId, userProfile);

        // then
        verify(userProfileRepository).update(userProfile);
    }
}