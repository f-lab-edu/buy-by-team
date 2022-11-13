package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{

    private static Map<Long, User> userDb = new HashMap<>();

    // 이메일조회 성능을 위한 인덱스 대용 해시맵
    private static Map<String, Long> userEmailIndex = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        userDb.put(user.getId(), user);
        userEmailIndex.put(user.getEmail(), user.getId());

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userDb.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Long id = userEmailIndex.get(email);

        return Optional.ofNullable(userDb.get(id));
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Long id = userEmailIndex.get(email);

        if(userDb.get(id).matchPassword(password)){
            return Optional.of(userDb.get(id));
        }else{
            return Optional.empty();
        }
    }
}