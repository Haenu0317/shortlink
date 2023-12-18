package com.haenu.shortlink.dto.req;

import lombok.Data;

/**
 * 用户登录请求信息类
 **/
@Data
public class UserLoginReqDTO {

    //用户名
    private String username;

    //密码
    private String password;
}
