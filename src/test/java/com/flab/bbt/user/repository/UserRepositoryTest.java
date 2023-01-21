package com.flab.bbt.user.repository;

import com.flab.bbt.AbstractContainerBaseTest;
import com.flab.bbt.ConfigureTestProfile;
import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ConfigureTestProfile
class UserRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private UserRepository userRepository;

    private String email = "test@test.com";

    @Test
    @DisplayName("user 정보가 저장소에 성공적으로 저장된다.")
    void saveSuccessTest() {
        // given
        User user = buildUser();

        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(user.getPassword()).isEqualTo(savedUser.getPassword());
    }

    @Test
    @DisplayName("userProfile 정보가 저장소에 성공적으로 저장된다.")
    void saveUserProfileSuccessTest() {
        // given
        UserProfile userProfile = buildUserProfile();

        // when
        UserProfile savedUserProfile = userRepository.saveUserProfile(userProfile);

        // then
        assertThat(savedUserProfile.getId()).isNotNull();
        assertThat(userProfile.getUserId()).isEqualTo(savedUserProfile.getUserId());
        assertThat(userProfile.getName()).isEqualTo(savedUserProfile.getName());
        assertThat(userProfile.getPhoneNo()).isEqualTo(savedUserProfile.getPhoneNo());
    }

    @Test
    void findByIdSuccessTest() {
        // given
        User savedUser = userRepository.save(buildUser());

        // when
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        // then
        assertThat(foundUser.get().getId()).isEqualTo(savedUser.getId());
    }

    @Test
    void findByEmailSuccessTest() {
        // given
        User savedUser = userRepository.save(buildUser());

        // when
        Optional<User> result = userRepository.findByEmail(email);

        // then
        assertThat(result.get().getEmail()).isEqualTo(email);
    }

    private User buildUser() {
        return User.builder()
            .email(email)
            .password("encryptedPassword")
            .build();
    }

    private UserProfile buildUserProfile() {
        return UserProfile.builder()
            .name("test")
            .phoneNo("01012341234")
            .userId(buildUser().getId())
            .build();
    }

    private UserProfile buildUpdatedUserProfile() {
        return UserProfile.builder()
            .id(1L)
            .name("updatedName")
            .phoneNo("01043214321")
            .userId(buildUser().getId())
            .build();
    }
}