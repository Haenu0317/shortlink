package com.haenu.shortlink.project.service;

import com.haenu.shortlink.project.dao.entity.ShortLinkDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
* @author Haenu0317
* @description 针对表【t_link】的数据库操作Service
* @createDate 2023-12-20 10:57:56
*/
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param requestParam
     * @return
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
