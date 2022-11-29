package com.flab.bbt.auth.request;

import com.flab.bbt.user.domain.User;

import com.flab.bbt.user.domain.UserProfile;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    private String password;
    private String name;

    public User convertToEntity() {
        return User.builder()
            .email(this.getEmail())
            .encryptedPassword(this.getPassword())
            .userProfile(getUserProfile())
            .build();
    }

    private UserProfile getUserProfile() {
        return new UserProfile(
            (this.getName() == "" || this.getName() == null) ? this.getEmail().split("@")[0]
                : this.getName()
        );
    }
}
