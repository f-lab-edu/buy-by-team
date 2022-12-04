package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;

import com.flab.bbt.user.domain.UserProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository{

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    User update(User user);
}