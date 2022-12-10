package com.flab.bbt.user.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.repository.UserProfileRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public void update(Long userId, UserProfile updateUserProfile) {
        Optional<UserProfile> userProfile = Optional.of(
            userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    return new CustomException(ErrorCode.USE_RPROFILE_NOT_FOUNT);
                }));

        userProfile.get().update(updateUserProfile);

        userProfileRepository.update(userProfile.get());
    }

}
