package com.haenu.shortlink.dto.req;

import lombok.Data;

/**
 * 修改分组请求实体
 *
 * @author haenu
 * @version 1.0
 * @date 2023/12/19 9:00
 */
@Data
public class GroupUpdateReqDTO {
    /**
     * 分组表示
     */
    private String gid;

    /**
     *
     */
    private String name;

}
