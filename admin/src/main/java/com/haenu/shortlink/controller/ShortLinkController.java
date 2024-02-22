package com.haenu.shortlink.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.common.convention.result.Results;
import com.haenu.shortlink.remote.dto.ShortLinkRemoteService;
import com.haenu.shortlink.remote.dto.req.ShortLinkBatchCreateReqDTO;
import com.haenu.shortlink.remote.dto.req.ShortLinkCreateReqDTO;
import com.haenu.shortlink.remote.dto.req.ShortLinkPageReqDTO;
import com.haenu.shortlink.remote.dto.req.ShortLinkUpdateReqDTO;
import com.haenu.shortlink.remote.dto.resp.ShortLinkBaseInfoRespDTO;
import com.haenu.shortlink.remote.dto.resp.ShortLinkBatchCreateRespDTO;
import com.haenu.shortlink.remote.dto.resp.ShortLinkCreateRespDTO;
import com.haenu.shortlink.remote.dto.resp.ShortLinkPageRespDTO;
import com.haenu.shortlink.toolkit.EasyExcelWebUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 批量创建短链接
     */
    @SneakyThrows
    @PostMapping("/api/short-link/admin/v1/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response, "批量创建短链接-SaaS短链接系统", ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }

    /**
     * 修改短链接
     */
    @PostMapping("/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }
}
