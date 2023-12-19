package com.haenu.shortlink.controller;

import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.common.convention.result.Results;
import com.haenu.shortlink.dto.req.UserLoginReqDTO;
import com.haenu.shortlink.dto.req.UserRegisterDTO;
import com.haenu.shortlink.dto.req.UserUpdateDTO;
import com.haenu.shortlink.dto.resp.UserLoginRespDTO;
import com.haenu.shortlink.dto.resp.UserRespDto;
import com.haenu.shortlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/short-link/admin/v1/user")
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
     *
     * @param userRegisterDTO 注册入参
     * @return
     */
    @PostMapping
    public Result<Void> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.userRegister(userRegisterDTO);
        return Results.success();
    }

    /**
     * 修改用户信息
     *
     * @param userUpdateDTO
     * @return
     */
    @PutMapping
    public Result<Void> update(@RequestBody UserUpdateDTO userUpdateDTO) {
        userService.updateByUserName(userUpdateDTO);
        return Results.success();
    }

    /**
     * 用户登录
     *
     * @param userLoginReqDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO) {
        UserLoginRespDTO userLoginRespDTO = userService.login(userLoginReqDTO);
        return Results.success(userLoginRespDTO);
    }

    /**
     * 检查用户是否登录
     *
     * @param token
     * @return
     */
    @GetMapping("/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));

    }

    /**
     * 用户退出
     *
     * @param username
     * @param token
     * @return
     */
    @DeleteMapping("/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }

}
