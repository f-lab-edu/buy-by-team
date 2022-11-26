package com.flab.bbt.user.request;

import com.flab.bbt.user.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String name;
    private String phoneNo;

    public UserInfo convertToUserInfo() {
        return UserInfo.builder()
            .name(this.getName())
            .phoneNo(this.getPhoneNo())
            .build();
    }
}
