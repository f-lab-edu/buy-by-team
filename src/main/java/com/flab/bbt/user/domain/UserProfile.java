package com.flab.bbt.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserProfile {

    private Long id;
    private String name;
    private String phoneNo;
    private Long userId;

    public UserProfile(String name) {
        this.name = name;
    }

    public void update(UserProfile userProfile) {
        this.name = userProfile.getName();
        this.phoneNo = userProfile.getPhoneNo();
    }
}
