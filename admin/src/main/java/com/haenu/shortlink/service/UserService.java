package com.haenu.shortlink.service;

import com.haenu.shortlink.dao.entity.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.shortlink.dto.resp.UserRespDto;

/**
* @author Haenu0317
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-12-17 10:32:14
*/
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名返回结果
     * @param username 用户名
     * @return 用户返回信息
     */
    UserRespDto getUserByUsername(String username);

}
