package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.mybatis.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisUserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        userMapper.save(user);

        return user;
    }

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        userMapper.saveUserProfile(userProfile);

        return userProfile;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = userMapper.findByEmail(email);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        List<User> result = userMapper.findByEmailAndPassword(email, password);
        log.info("result", result);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public Optional<UserProfile> findUserProfileByUserId(Long userId) {
        return userMapper.findUserProfileByUserId(userId);
    }

    @Override
    public User update(User user) {
        userMapper.update(user.getId(), user);

        return user;
    }

    @Override
    public UserProfile updateUserProfile(UserProfile userProfile) {
        userMapper.updateUserProfile(userProfile);

        return userProfile;
    }
}
