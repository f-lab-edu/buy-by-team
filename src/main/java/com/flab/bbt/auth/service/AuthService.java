package com.flab.bbt.auth.service;

import com.flab.bbt.exception.UserNotFoundException;
import com.flab.bbt.exception.WrongPasswordException;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
//    #TODO("INJECTION 방식 고민")
    @Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }else if(!user.get().matchPassword(password)){
            throw new WrongPasswordException();
        }

        return user;
    }
}
