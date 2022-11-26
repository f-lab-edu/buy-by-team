package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    UserRepositoryImpl repo = new UserRepositoryImpl();

    @Test
    void findById() {
        User user1 = User.builder()
            .email("test@test.com")
            .encryptedPassword("1234")
            .build();

        repo.save(user1);
        Optional<User> result = repo.findById(user1.getId());

        assertThat(result.get()).isEqualTo(user1);
    }

    @Test
    void findByEmail() {
        User user1 = User.builder()
            .email("test@test.com")
            .encryptedPassword("1234")
            .build();

        repo.save(user1);
        Optional<User> result = repo.findByEmail("test@test.com");

        assertThat(result.get()).isEqualTo(user1);
    }

}