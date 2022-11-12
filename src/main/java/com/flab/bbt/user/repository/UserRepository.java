package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}