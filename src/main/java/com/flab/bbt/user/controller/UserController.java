package com.flab.bbt.user.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.request.UpdateUserRequest;
import com.flab.bbt.user.request.UserProfileRequest;
import com.flab.bbt.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/{id}/user-profiles")
    public CommonResponse createUserProfile(@PathVariable Long id,
        @RequestBody UserProfileRequest userProfileRequest, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.COOKIE_SESSION_ID);

        if (user.getId() != id) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        UserProfile userProfile = userProfileRequest.convertToUserProfile(user);
        userService.createUserProfile(user.getId(), userProfile);

        return CommonResponse.success();
    }

    @PatchMapping("/user-profiles")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateUserProfiile(@RequestBody UpdateUserRequest updateUserRequest,
        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.COOKIE_SESSION_ID);
        UserProfile userProfile = updateUserRequest.convertToUserProfile();
        userService.updateUserProfile(user.getId(), userProfile);

        return CommonResponse.success();
    }
}
