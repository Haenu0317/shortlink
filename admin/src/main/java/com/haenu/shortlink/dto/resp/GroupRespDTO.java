package com.haenu.shortlink.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 分组返回实体类
 * @author haenu
 * @version 1.0
 * @date 2023/12/19 9:24
 */
@Data
public class GroupRespDTO {
    /**
     * 分组标识
     */
    @TableField(value = "gid")
    private String gid;

    /**
     * 分组名称
     */
    @TableLogic
    @TableField(value = "name")
    private String name;

    /**
     * 创建分组用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 分组排序
     */
    @TableField(value = "sort_order")
    private Integer sortOrder;

}
