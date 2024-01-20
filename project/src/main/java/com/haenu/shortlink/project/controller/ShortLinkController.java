package com.haenu.shortlink.project.controller;

import com.haenu.shortlink.project.common.convention.result.Result;
import com.haenu.shortlink.project.common.convention.result.Results;
import com.haenu.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.haenu.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接控制层
 *
 * @author haenu
 * @version 1.0
 * @date 2023/12/20 13:38
 */
@RestController
@RequestMapping("/api/short-link/admin/v1")
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }


}
