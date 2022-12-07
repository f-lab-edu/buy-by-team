package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;

import com.flab.bbt.user.domain.UserProfile;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    User update(User user);
}