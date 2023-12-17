package com.haenu.shortlink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.common.convention.exception.ClientException;
import com.haenu.shortlink.common.enums.UserErrorCodeEnum;
import com.haenu.shortlink.dao.entity.UserDO;
import com.haenu.shortlink.dao.mapper.UserMapper;
import com.haenu.shortlink.dto.resp.UserRespDto;
import com.haenu.shortlink.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Haenu0317
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-12-17 10:32:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO>
        implements UserService {


    /**
     * 根据用户名返回结果
     *
     * @param username 用户名
     * @return 用户返回信息
     */
    @Override
    public UserRespDto getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDto userRespDto = new UserRespDto();
        BeanUtil.copyProperties(userDO, userRespDto);
        return userRespDto;
    }
}




