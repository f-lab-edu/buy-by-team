package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{

    private static Map<Long, User> userDb = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        userDb.put(user.getId(), user);

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userDb.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDb.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }
}
