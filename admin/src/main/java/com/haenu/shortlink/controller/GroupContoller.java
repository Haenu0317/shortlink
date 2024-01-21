package com.haenu.shortlink.controller;

import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.common.convention.result.Results;
import com.haenu.shortlink.dto.req.GroupSaveReqDTO;
import com.haenu.shortlink.dto.req.GroupSortReqDTO;
import com.haenu.shortlink.dto.req.GroupUpdateReqDTO;
import com.haenu.shortlink.dto.resp.ShortLinkGroupRespDTO;
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

    /**
     * 新增短链接分组
     * @param requestParm
     * @return
     */
    @PostMapping
    public Result<Void> save(@RequestBody GroupSaveReqDTO requestParm) {
        groupService.saveGroup(requestParm.getName());
        return Results.success();
    }

    /**
     * 获取分组列表
     * @return
     */
    @GetMapping
    public Result<List<ShortLinkGroupRespDTO>> getAll() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改分组信息
     * @param requestParm
     * @return
     */
    @PutMapping
    public Result<Void> update(@RequestBody GroupUpdateReqDTO requestParm) {
        groupService.updateGroup(requestParm);
        return Results.success();
    }

    /**
     * 删除短链接分组
     * @param gid
     * @return
     */
    @DeleteMapping
    public Result<Void> delete(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 排序
     * @param requestParm
     * @return
     */
    @PostMapping("/sort")
    public Result<Void> sortGroup(@RequestBody List<GroupSortReqDTO> requestParm) {
        groupService.sortGroup(requestParm);
        return Results.success();
    }

}
