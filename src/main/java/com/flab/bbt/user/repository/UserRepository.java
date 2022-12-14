package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void saveUserProfile(UserProfile userProfile);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<UserProfile> findUserProfileByUserId(Long userId);

    int update(User user);

    int updateUserProfile(UserProfile userProfile);
}