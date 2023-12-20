package com.haenu.shortlink.dto.req;

import lombok.Data;

/**
 * 分组排序请求实体
 *
 * @author haenu
 * @version 1.0
 * @date 2023/12/19 9:00
 */
@Data
public class GroupSortReqDTO {
    /**
     * 分组ID
     */
    private String gid;

    /**
     * 排序
     */
    private Integer sortOrder;
}
