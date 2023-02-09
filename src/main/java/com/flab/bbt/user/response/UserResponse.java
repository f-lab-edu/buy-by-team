package com.flab.bbt.user.response;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserResponse {

    private Long id;
    private String email;
    private UserProfile userProfile;

    public static UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .userProfile(user.getUserProfile())
            .build();
    }
}
