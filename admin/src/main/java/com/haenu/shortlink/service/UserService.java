package com.haenu.shortlink.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.shortlink.dao.entity.UserDO;
import com.haenu.shortlink.dto.req.UserLoginReqDTO;
import com.haenu.shortlink.dto.req.UserRegisterDTO;
import com.haenu.shortlink.dto.req.UserUpdateDTO;
import com.haenu.shortlink.dto.resp.UserLoginRespDTO;
import com.haenu.shortlink.dto.resp.UserRespDto;

/**
 * @author Haenu0317
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2023-12-17 10:32:14
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名返回结果
     *
     * @param username 用户名
     * @return 用户返回信息
     */
    UserRespDto getUserByUsername(String username);

    /**
     * 检查用户名是否存在可用
     *
     * @param username
     * @return
     */
    Boolean hasUserName(String username);

    /**
     * 新增用户
     *
     * @param requestParm
     */
    void userRegister(UserRegisterDTO requestParm);

    /**
     * 修改用户信息
     *
     * @param userUpdateDTO
     */
    void updateByUserName(UserUpdateDTO userUpdateDTO);

    /**
     * 用户登录
     *
     * @param userLoginReqDTO
     * @return
     */
    UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO);

    /**
     * 检验token
     *
     * @param token
     * @return
     */
    Boolean checkLogin(String username, String token);

    /**
     * 用户退出
     *
     * @param username
     * @param token
     * @return
     */
    void logout(String username, String token);
}
