package com.flab.bbt.user.response;

import com.flab.bbt.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserSignUpResponse {

    private Long id;
    private String email;

    public static UserSignUpResponse convertToUserSignUpResponse(User user) {
        return UserSignUpResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .build();
    }
}
