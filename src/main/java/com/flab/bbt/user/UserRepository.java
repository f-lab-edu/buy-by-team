package com.flab.bbt.user;

import com.flab.bbt.user.domain.User;

import java.util.HashMap;

public interface UserRepository {

    public User findById(Long id);

    public User save(User user);
}
