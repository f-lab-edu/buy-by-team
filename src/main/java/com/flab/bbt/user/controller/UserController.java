package com.flab.bbt.user.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.common.SessionConst;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserProfile;
import com.flab.bbt.user.request.UpdateUserRequest;
import com.flab.bbt.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/user-profiles")
    public CommonResponse updateUserProfiile(@RequestBody UpdateUserRequest updateUserRequest,
        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SessionConst.COOKIE_SESSION_ID);

        UserProfile userProfile = updateUserRequest.convertToUserProfile();
        userService.update(user, userProfile);

        return CommonResponse.success();
    }
}
