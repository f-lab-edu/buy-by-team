package com.flab.bbt.user.repository.mybatis;

import com.flab.bbt.user.domain.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void save(User user);

    Optional<User> findById(Long id);

}
