package com.flab.bbt.user.repository.mybatis;

import com.flab.bbt.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void save(User user);

    Optional<User> findById(Long id);

    List<User> findByEmail(String email);

    List<User> findByEmailAndPassword(@Param("email") String email,@Param("password") String password);

}