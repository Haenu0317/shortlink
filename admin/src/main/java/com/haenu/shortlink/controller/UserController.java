package com.haenu.shortlink.controller;

import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.common.convention.result.Results;
import com.haenu.shortlink.dto.req.UserRegisterDTO;
import com.haenu.shortlink.dto.resp.UserRespDto;
import com.haenu.shortlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/short-link/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public Result<UserRespDto> getUserByUsername(@PathVariable("username") String username) {
        UserRespDto result = userService.getUserByUsername(username);
        return Results.success(result);
    }
    @GetMapping("/has-username")
    public Result<Boolean> hasUserName(@RequestParam("username") String username) {
        return Results.success(userService.hasUserName(username));
    }

    /**
     * 注册用户
     * @param userRegisterDTO 注册入参
     * @return
     */
    @PostMapping
    public Result<Void> register(@RequestBody UserRegisterDTO userRegisterDTO){
        userService.userRegister(userRegisterDTO);
        return Results.success();
    }
}
