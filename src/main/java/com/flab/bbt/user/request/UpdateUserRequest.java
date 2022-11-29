package com.flab.bbt.user.request;

import com.flab.bbt.user.domain.UserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String name;
    private String phoneNo;

    public UserProfile convertToUserProfile() {
        return UserProfile.builder()
            .name(this.getName())
            .phoneNo(this.getPhoneNo())
            .build();
    }
}
