package com.haenu.shortlink.dto.req;

import lombok.Data;

/**
 * @author haenu
 * @version 1.0
 * @date 2023/12/18 14:22
 */
@Data
public class UserUpdateDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
