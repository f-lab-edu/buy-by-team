package com.flab.bbt.user.repository.mybatis;

import com.flab.bbt.user.domain.UserProfile;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfileMapper {
    void save(UserProfile userProfile);
    void update(UserProfile userProfile);
    Optional<UserProfile> findByUserId(Long userId);
}
