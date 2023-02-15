package com.flab.bbt.auth.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void signUp(User user) {
        if (isDuplicatedEmail(user.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        userRepository.save(user);
    }

    private boolean isDuplicatedEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Long authenticate(User user) {
        Optional<User> foundUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (foundUser.isEmpty()) {
            return null;
        } else {
            return foundUser.get().getId();
        }
    }
}
