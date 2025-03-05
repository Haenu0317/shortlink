package com.haenu.shortlink.controller;

import cn.hutool.core.bean.BeanUtil;
import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.common.convention.result.Results;
import com.haenu.shortlink.dto.req.UserLoginReqDTO;
import com.haenu.shortlink.dto.req.UserRegisterDTO;
import com.haenu.shortlink.dto.req.UserUpdateDTO;
import com.haenu.shortlink.dto.resp.UserActualRespDTO;
import com.haenu.shortlink.dto.resp.UserLoginRespDTO;
import com.haenu.shortlink.dto.resp.UserRespDto;
import com.haenu.shortlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDto> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUserName(@RequestParam("username") String username) {
        return Results.success(userService.hasUserName(username));
    }

    /**
     * 根据用户名查询无脱敏用户信息
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 注册用户
     *
     * @param userRegisterDTO 注册入参
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/user")
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
    @PutMapping("/api/short-link/admin/v1/user")
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
    @PostMapping("/api/short-link/admin/v1/user/login")
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
    @GetMapping("/api/short-link/admin/v1/user/check-login")
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
    @DeleteMapping("/api/short-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }

}
