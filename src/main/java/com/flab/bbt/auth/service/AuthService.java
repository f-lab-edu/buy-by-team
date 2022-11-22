package com.flab.bbt.auth.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncrypter passwordEncrypter;

    public AuthService(UserRepository userRepository, PasswordEncrypter passwordEncrypter) {
        this.userRepository = userRepository;
        this.passwordEncrypter = passwordEncrypter;
    }

    public void signUp(User user) {
        if(isDuplicatedEmail(user.getEmail())){
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        userRepository.save(user);
    }

    private boolean isDuplicatedEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User authenticate(User user){
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getEncryptedPassword())
                .orElseThrow(()-> {return new CustomException(ErrorCode.USER_NOT_FOUND);} );
    }
}
