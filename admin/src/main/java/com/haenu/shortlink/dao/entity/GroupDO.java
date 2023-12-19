package com.haenu.shortlink.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.haenu.shortlink.common.database.BaseDO;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 短链接分组实体
 *
 * @TableName t_group
 */
@Data
@Builder
@TableName(value = "t_group")
public class GroupDO extends BaseDO implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}