package com.haenu.shortlink.dto.resp;

import lombok.Data;

/**
 * 用户信息响应类
 *
 * @author: haenu
 * @date: 2023/12/17 10:39
 **/
@Data
public class UserRespDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
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
