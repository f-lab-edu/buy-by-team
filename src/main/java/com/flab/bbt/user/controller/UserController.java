package com.flab.bbt.user.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.user.domain.User;
import com.flab.bbt.user.domain.UserInfo;
import com.flab.bbt.user.request.UpdateUserRequest;
import com.flab.bbt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/{id}/user-info")
    public CommonResponse updateUserInfo(@RequestBody UpdateUserRequest request, @PathVariable long id) {
        User user = userService.findUserById(id);
        UserInfo userInfo = request.convertToUserInfo();

        userService.update(user, userInfo);

        return CommonResponse.success();
    }
}
