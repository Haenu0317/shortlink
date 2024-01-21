package com.haenu.shortlink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.common.biz.user.UserContext;
import com.haenu.shortlink.common.convention.result.Result;
import com.haenu.shortlink.dao.entity.GroupDO;
import com.haenu.shortlink.dao.mapper.GroupMapper;
import com.haenu.shortlink.dto.req.GroupSortReqDTO;
import com.haenu.shortlink.dto.req.GroupUpdateReqDTO;
import com.haenu.shortlink.dto.resp.ShortLinkGroupRespDTO;
import com.haenu.shortlink.remote.dto.ShortLinkRemoteService;
import com.haenu.shortlink.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.haenu.shortlink.service.GroupService;
import com.haenu.shortlink.toolkit.RandomStringGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Haenu0317
 * @description 针对表【t_group】的数据库操作Service实现
 * @createDate 2023-12-18 22:15:26
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO>
        implements GroupService {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 新增分组
     *
     * @param groupName 分组名
     */
    @Override
    public void saveGroup(String groupName) {
        this.saveGroup(UserContext.getUsername(), groupName);
    }

    /**
     * 新增分组
     *
     * @param groupName 分组名
     */
    @Override
    public void saveGroup(String username, String groupName) {
        String gid;
        while (true) {
            gid = RandomStringGenerator.generateRandomString();
            if (!hasGid(username, gid)) {
                break;
            }
        }
        GroupDO groupDO = GroupDO.builder()
                .gid(RandomStringGenerator.generateRandomString())
                .sortOrder(0)
                .name(groupName)
                .username(username)
                .build();
        save(groupDO);
    }

    /**
     * 查询所有分组
     *
     * @return 所有分组
     */
    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        Result<List<ShortLinkGroupCountQueryRespDTO>> listResult = shortLinkRemoteService
                .listGroupShortLinkCount(groupDOList.stream().map(GroupDO::getGid).toList());
        List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOList = BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);
        shortLinkGroupRespDTOList.forEach(each -> {
            Optional<ShortLinkGroupCountQueryRespDTO> first = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
        });
        return shortLinkGroupRespDTOList;
    }

    /**
     * 修改分组信息
     *
     * @param requestParm
     */
    @Override
    public void updateGroup(GroupUpdateReqDTO requestParm) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getGid, requestParm.getGid())
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO groupDO = GroupDO.builder().build();
        BeanUtil.copyProperties(requestParm, groupDO);
        update(groupDO, updateWrapper);

    }

    /**
     * 删除分组信息
     *
     * @param requestParm
     */
    @Override
    public void deleteGroup(String requestParm) {
        LambdaQueryWrapper<GroupDO> deleteQueryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, requestParm)
                .eq(GroupDO::getUsername, UserContext.getUsername());

        remove(deleteQueryWrapper);
    }

    /**
     * 短链接分组排序
     *
     * @param requestParm
     */
    @Override
    public void sortGroup(List<GroupSortReqDTO> requestParm) {
        requestParm.forEach(each -> {
            GroupDO groupDO = GroupDO.builder()
                    .sortOrder(each.getSortOrder())
                    .build();
            LambdaUpdateWrapper<GroupDO> lambdaUpdateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, each.getGid());
            update(groupDO, lambdaUpdateWrapper);
        });
    }

    /**
     * 根据gid检查该分组存在
     *
     * @param gid
     * @return
     */

    private boolean hasGid(String username, String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, Optional.ofNullable(username).orElse(UserContext.getUsername()));
        GroupDO groupDO = baseMapper.selectOne(queryWrapper);
        return groupDO != null;
    }
}




