package com.haenu.shortlink.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.remote.dto.ShortLinkRemoteService;
import com.haenu.shortlink.remote.dto.req.ShortLinkCreateReqDTO;
import com.haenu.shortlink.remote.dto.req.ShortLinkPageReqDTO;
import com.haenu.shortlink.remote.dto.resp.ShortLinkCreateRespDTO;
import com.haenu.shortlink.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author haenu
 * @version 1.0
 * @date 2024/01/21 11:51
 */
@RestController
@RequestMapping("/api/short-link/admin/v1")
public class ShortLinkController {
    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     */
    @PostMapping("/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
         return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
