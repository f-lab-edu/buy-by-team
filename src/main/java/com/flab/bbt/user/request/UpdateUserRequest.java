package com.flab.bbt.user.request;

import com.flab.bbt.user.domain.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
