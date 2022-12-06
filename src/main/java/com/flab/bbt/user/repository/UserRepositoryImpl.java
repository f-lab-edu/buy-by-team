package com.flab.bbt.user.repository;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private static Map<Long, User> userDb = new ConcurrentHashMap<>();

    // 이메일조회 성능을 위한 인덱스 대용 해시맵
    private static Map<String, Long> userEmailIndex = new HashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public void save(User user) {
//        userMapper.save(user);
        user.setId(sequence.incrementAndGet());
        userDb.put(user.getId(), user);
        userEmailIndex.put(user.getEmail(), user.getId());
    }

    @Override
    public Optional<User> findById(Long id) {
//        return userMapper.findById(id);
        return Optional.ofNullable(userDb.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Long id = userEmailIndex.get(email);

        if (id != null) {
            return Optional.of(userDb.get(id));
        } else {
            return Optional.empty();
        }
//        return userMapper.findByEmail(email).isEmpty() ? Optional.empty() : Optional.of(userMapper.findByEmail(email).get(0));
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Long id = userEmailIndex.get(email);

        if (userDb.get(id).matchPassword(password)) {
            return Optional.of(userDb.get(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public User update(User user) {
        userDb.replace(user.getId(), user);

        return user;
    }
}