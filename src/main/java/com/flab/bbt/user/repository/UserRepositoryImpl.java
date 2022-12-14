package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static Map<Long, User> userDb = new ConcurrentHashMap<>();

    // 이메일조회 성능을 위한 인덱스 대용 해시맵
    private static Map<String, Long> userEmailIndex = new HashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public User save(User user) {
        user.setId(sequence.incrementAndGet());
        userDb.put(user.getId(), user);
        userEmailIndex.put(user.getEmail(), user.getId());

        return user;
    }

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfile;
    }

    @Override
    public Optional<User> findById(Long id) {
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
    public Optional<UserProfile> findUserProfileByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        userDb.replace(user.getId(), user);
        return user;
    }

    @Override
    public UserProfile updateUserProfile(UserProfile userProfile) {
        return userProfile;
    }
}