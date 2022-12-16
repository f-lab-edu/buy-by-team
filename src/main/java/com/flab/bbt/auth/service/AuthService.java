package com.flab.bbt.auth.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncrypter passwordEncrypter;

    public void signUp(User user) {
        if (isDuplicatedEmail(user.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        userRepository.save(user);
    }

    private boolean isDuplicatedEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User authenticate(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())
            .orElseThrow(() -> {
                return new CustomException(ErrorCode.USER_NOT_FOUND);
            });
    }
}
