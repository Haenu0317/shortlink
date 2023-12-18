package com.haenu.shortlink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.common.convention.exception.ClientException;
import com.haenu.shortlink.common.enums.UserErrorCodeEnum;
import com.haenu.shortlink.dao.entity.UserDO;
import com.haenu.shortlink.dao.mapper.UserMapper;
import com.haenu.shortlink.dto.req.UserLoginReqDTO;
import com.haenu.shortlink.dto.req.UserRegisterDTO;
import com.haenu.shortlink.dto.req.UserUpdateDTO;
import com.haenu.shortlink.dto.resp.UserLoginRespDTO;
import com.haenu.shortlink.dto.resp.UserRespDto;
import com.haenu.shortlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.haenu.shortlink.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.haenu.shortlink.common.enums.UserErrorCodeEnum.*;

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

    private final RedissonClient redissonClient;

    private final StringRedisTemplate stringRedisTemplate;

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
        if (!hasUserName(requestParm.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParm.getUsername());
        try {
            if (lock.tryLock()) {
                int insert = baseMapper.insert(BeanUtil.toBean(requestParm, UserDO.class));
                if (insert < 1) {
                    throw new ClientException(USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParm.getUsername());
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }

    }

    /**
     * 修改用户信息
     *
     * @param userUpdateDTO
     */
    @Override
    public void updateByUserName(UserUpdateDTO userUpdateDTO) {
        //todo 检查当前修改的用户信息是否是我们的登录用户
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userUpdateDTO.getUsername());
        update(BeanUtil.toBean(userUpdateDTO, UserDO.class), queryWrapper);
    }

    /**
     * 用户登录
     *
     * @param userLoginReqDTO
     * @return
     */

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userLoginReqDTO.getUsername())
                .eq(UserDO::getPassword, userLoginReqDTO.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO user = baseMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new ClientException(USER_NULL);
        }

        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey("login_" + userLoginReqDTO.getUsername()))) {
            throw new ClientException("用户已经登录");
        }

        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + userLoginReqDTO.getUsername(), uuid, JSON.toJSONString(user));
        stringRedisTemplate.expire("login_" + userLoginReqDTO.getUsername(), 30L, TimeUnit.MINUTES);
        return new UserLoginRespDTO(uuid);
    }

    /**
     * 检验token
     *
     * @param token
     * @return
     */
    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get("login_" + username, token) != null;
    }

    /**
     * 用户退出
     *
     * @param username
     * @param token
     * @return
     */
    @Override
    public void logout(String username, String token) {
        if (checkLogin(username, token)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException("Token不存在或用户未登录");
    }
}




