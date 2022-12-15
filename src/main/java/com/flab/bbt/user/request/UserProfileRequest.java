package com.flab.bbt.user.request;

import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequest {
    private String name;
    private String phoneNo;

    public UserProfile convertToUserProfile(User user) {
        return UserProfile.builder()
            .name(this.getName())
            .phoneNo(this.getPhoneNo())
            .userId(user.getId())
            .build();
    }
}
