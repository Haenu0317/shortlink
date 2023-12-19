package com.haenu.shortlink.controller;

import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.common.convention.result.Results;
import com.haenu.shortlink.dto.req.GroupSaveReqDTO;
import com.haenu.shortlink.dto.resp.GroupRespDTO;
import com.haenu.shortlink.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接分组Controller
 *
 * @author haenu
 * @version 1.0
 * @date 2023/12/18 22:19
 */

@RestController
@RequestMapping("/api/short-link/admin/v1/group")
@RequiredArgsConstructor
public class GroupContoller {

    private final GroupService groupService;

    @PostMapping
    public Result<Void> save(@RequestBody GroupSaveReqDTO requestParm) {
        groupService.saveGroup(requestParm.getName());
        return Results.success();
    }

    @GetMapping
    public Result<List<GroupRespDTO>> getAll() {
        return Results.success(groupService.listGroup());
    }


}
