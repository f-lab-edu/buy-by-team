package com.flab.bbt.user.repository;

import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.mybatis.UserProfileMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisUserProfileRepositoryImpl implements UserProfileRepository{

    private final UserProfileMapper userProfileMapper;

    @Override
    public void save(UserProfile userProfile) {
        userProfileMapper.save(userProfile);
    }

    @Override
    public void update(UserProfile userProfile) {
        userProfileMapper.update(userProfile);
    }

    @Override
    public Optional<UserProfile> findByUserId(Long userId) {
        return userProfileMapper.findByUserId(userId);
    }
}
