package com.flab.bbt.user.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUserProfile(long userId, UserProfile userProfile) {
        if (checkIfUserProfileExist(userId)) {
            throw new CustomException(ErrorCode.USER_PROFILE_ALREADY_EXISTS);
        }
        userRepository.saveUserProfile(userProfile);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public UserProfile findUserProfileByUserId(Long userId) {
        return userRepository.findUserProfileByUserId(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USE_RPROFILE_NOT_FOUNT));
    }

    public void updateUserProfile(Long userId, UserProfile updateUserProfile) {
        UserProfile userProfile = findUserProfileByUserId(userId);
        userProfile.update(updateUserProfile);

        userRepository.updateUserProfile(userProfile);
    }

    private boolean checkIfUserProfileExist(Long userId) {
        return userRepository.findUserProfileByUserId(userId).isPresent();
    }
}
