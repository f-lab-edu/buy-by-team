package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.UserProfile;
import java.util.Optional;

public interface UserProfileRepository {
    void save(UserProfile userProfile);
    void update(UserProfile userProfile);
    Optional<UserProfile> findByUserId(Long userId);
}
