package com.haenu.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haenu.shortlink.project.common.convention.result.Result;
import com.haenu.shortlink.project.common.convention.result.Results;
import com.haenu.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.haenu.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.haenu.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.haenu.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.haenu.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接控制层
 *
 * @author haenu
 * @version 1.0
 * @date 2023/12/20 13:38
 */
@RestController
@RequestMapping("/api/short-link/v1")
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

    /**
     * 修改短链接
     */
    @PutMapping("/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * 查询短链接分组内数量
     */
    @GetMapping("count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }


}
