package com.haenu.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haenu.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 短链接基础访问监控实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_link_access_stats")
public class LinkAccessStatsDO extends BaseDO {
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
     * 完整短链接
     */
    @TableField(value = "full_short_url")
    private String fullShortUrl;

    /**
     * 日期
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * 访问量
     */
    @TableField(value = "pv")
    private Integer pv;

    /**
     * 独立访问数
     */
    @TableField(value = "uv")
    private Integer uv;

    /**
     * 独立IP数
     */
    @TableField(value = "uip")
    private Integer uip;

    /**
     * 小时
     */
    @TableField(value = "hour")
    private Integer hour;

    /**
     * 星期
     */
    @TableField(value = "weekday")
    private Integer weekday;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标识：0 未删除 1 已删除
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
