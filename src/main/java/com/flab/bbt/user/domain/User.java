package com.flab.bbt.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Setter
    private long id;
    private String email;

    @Setter
    private String encryptedPassword;
    private UserInfo userInfo;

    public boolean matchPassword(String inputPassword) {
        return getEncryptedPassword().equals(inputPassword);
    }

    public void update(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}