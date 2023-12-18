package com.haenu.shortlink.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haenu.shortlink.dao.entity.UserDO;
import com.haenu.shortlink.dao.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {
    @Resource
    private UserMapper userMapper;
    @Test
    void test(){
        LambdaQueryWrapper<UserDO> queryWrapper = new QueryWrapper<UserDO>().lambda().eq(UserDO::getUsername, "汤丽");
        userMapper.delete(queryWrapper);
    }

}