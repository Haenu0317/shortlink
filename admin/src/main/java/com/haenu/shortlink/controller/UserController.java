package com.haenu.shortlink.controller;

import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.common.convention.result.Results;
import com.haenu.shortlink.dto.resp.UserRespDto;
import com.haenu.shortlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shortlink/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public Result<UserRespDto> getUserByUsername(@PathVariable("username") String username) {
        UserRespDto result = userService.getUserByUsername(username);
        return Results.success(result);
    }
}
