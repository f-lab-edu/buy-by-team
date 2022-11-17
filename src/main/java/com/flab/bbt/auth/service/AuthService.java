package com.flab.bbt.auth.service;

import com.flab.bbt.auth.request.SignInRequest;
import com.flab.bbt.auth.request.SignUpRequest;
import com.flab.bbt.exception.UserNotFoundException;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncrypter passwordEncrypter;

    public AuthService(UserRepository userRepository, PasswordEncrypter passwordEncrypter) {
        this.userRepository = userRepository;
        this.passwordEncrypter = passwordEncrypter;
    }

    public void signUp(SignUpRequest request) {
        User user = request.convertToEntity(request);

        // [ToDo]이메일 중복체크

        // password 암호화
        user.setPassword(passwordEncrypter.encrypt(user.getPassword()));
        userRepository.save(user);
    }

    public User authenticate(SignInRequest request){
        User user = request.convertToEntity(request);

        String encryptedPwd = passwordEncrypter.encrypt(user.getPassword());
        return userRepository.findByEmailAndPassword(user.getEmail(), encryptedPwd)
                .orElseThrow(UserNotFoundException::new);
    }
}
