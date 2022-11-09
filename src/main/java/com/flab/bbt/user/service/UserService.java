package com.flab.bbt.user.service;

import com.flab.bbt.user.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhoneNo(String phoneNo);


}
