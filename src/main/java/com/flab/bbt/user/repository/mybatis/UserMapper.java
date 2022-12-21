package com.flab.bbt.user.repository.mybatis;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void save(User user);

    void saveUserProfile(UserProfile userProfile);

    Optional<User> findById(Long id);

    List<User> findByEmail(String email);

    List<User> findByEmailAndPassword(@Param("email") String email,
        @Param("password") String password);

    Optional<UserProfile> findUserProfileByUserId(Long userId);

    int update(@Param("id") Long id, @Param("param") User user);

    int updateUserProfile(UserProfile userProfile);
}
