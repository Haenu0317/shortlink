package com.haenu.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.shortlink.project.dao.entity.ShortLinkDO;
import com.haenu.shortlink.project.dto.req.ShortLinkBatchCreateReqDTO;
import com.haenu.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.haenu.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.haenu.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkBatchCreateRespDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.util.List;

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

    /**
     * 批量创建短链接
     *
     * @param requestParam 批量创建短链接请求参数
     * @return 批量创建短链接返回参数
     */
    ShortLinkBatchCreateRespDTO batchCreateShortLink(ShortLinkBatchCreateReqDTO requestParam);

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 修改短链接
     * @param requestParam
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * 查询短链接分组内数量
     * @param requestParam
     * @return
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    /**
     * 短链接跳转
     * @param shortUri
     * @param request
     * @param response
     */
    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response);
}
