package com.haenu.shortlink.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.haenu.shortlink.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * URL 回收站接口层
 */
public interface RecycleBinService {

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 请求参数
     * @return 返回参数包装
     */
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
