package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;

import java.util.HashMap;

public class UserRepositoryImpl implements UserRepository {
    private static HashMap<Long, User> userDb = new HashMap();

    @Override
    public User findById(Long id){
        return userDb.get(id);
    }

    @Override
    public User save(User user){
        return userDb.put(user.getId(), user);
    }
}
