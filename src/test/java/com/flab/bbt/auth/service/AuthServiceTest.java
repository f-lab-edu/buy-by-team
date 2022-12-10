package com.flab.bbt.auth.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.UserProfileRepository;
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
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private PasswordEncrypter passwordEncrypter;

    User user;
    UserProfile userProfile;

    @BeforeEach
    public void setUp() {
        userProfile = UserProfile.builder().build();

        user = User.builder()
            .email("test@test.com")
            .password("encryptedPassword")
            .userProfile(userProfile)
            .build();
    }

    @Test
    @DisplayName("회원가입에 성공한다.")
    void signUpTest() {
        // given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        //when
        authService.signUp(user);

        //then
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("이메일 중복 시 회원가입 실패하며 예외가 발생한다.")
    void signUpWithDuplicatedEmailUserTest() {
        // given
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(user));

        // when
        CustomException e = assertThrows(CustomException.class,
            () -> authService.signUp(user));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.DUPLICATE_EMAIL.getMessage());
    }

    @Test
    @DisplayName("로그인에 성공한다.")
    void authenticateSuccessTest() {
        // given
        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.ofNullable(user));

        // when
        authService.authenticate(user);

        // then
        verify(userRepository).findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Test
    @DisplayName("로그인 실패시 예외가 발생한다.")
    void authenticateFailTest() {
        // given
        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.empty());

        // when
        CustomException e = assertThrows(CustomException.class,
            () -> authService.authenticate(user));

        // then
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.USER_NOT_FOUND.getMessage());
    }
}