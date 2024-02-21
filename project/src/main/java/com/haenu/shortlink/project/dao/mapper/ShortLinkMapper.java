package com.haenu.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haenu.shortlink.project.dao.entity.ShortLinkDO;
import com.haenu.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* @author Haenu0317
* @description 针对表【t_link】的数据库操作Mapper
* @createDate 2023-12-20 10:57:56
* @Entity generator.domain.TLink
*/
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    /**
     * 短链接访问统计自增
     */
    @Update("update t_link set total_pv = total_pv + #{totalPv}, total_uv = total_uv + #{totalUv}, total_uip = total_uip + #{totalUip} where gid = #{gid} and full_short_url = #{fullShortUrl}")
    void incrementStats(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("totalPv") Integer totalPv,
            @Param("totalUv") Integer totalUv,
            @Param("totalUip") Integer totalUip
    );

    /**
     * 分页统计短链接
     */
    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);
}




