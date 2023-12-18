package com.haenu.shortlink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.common.convention.exception.ClientException;
import com.haenu.shortlink.common.enums.UserErrorCodeEnum;
import com.haenu.shortlink.dao.entity.UserDO;
import com.haenu.shortlink.dao.mapper.UserMapper;
import com.haenu.shortlink.dto.req.UserRegisterDTO;
import com.haenu.shortlink.dto.resp.UserRespDto;
import com.haenu.shortlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Service;

import static com.haenu.shortlink.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static com.haenu.shortlink.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

/**
 * @author Haenu0317
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-12-17 10:32:14
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO>
        implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

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
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDto userRespDto = new UserRespDto();
        BeanUtil.copyProperties(userDO, userRespDto);
        return userRespDto;
    }

    /**
     * 检查用户名是否存在可用
     *
     * @param username
     * @return
     */
    @Override
    public Boolean hasUserName(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    /**
     * 新增用户
     *
     * @param requestParm
     */
    @Override
    public void userRegister(UserRegisterDTO requestParm) {
        if (!hasUserName(requestParm.getUsername())){
            throw new ClientException(USER_NAME_EXIST);
        }

        int insert = baseMapper.insert(BeanUtil.toBean(requestParm,UserDO.class));
        if (insert < 1){
            throw new ClientException(USER_SAVE_ERROR);
        }
        userRegisterCachePenetrationBloomFilter.add(requestParm.getUsername());

    }
}




