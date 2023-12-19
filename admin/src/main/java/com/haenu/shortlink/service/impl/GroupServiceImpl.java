package com.haenu.shortlink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.common.biz.user.UserContext;
import com.haenu.shortlink.dao.entity.GroupDO;
import com.haenu.shortlink.dao.mapper.GroupMapper;
import com.haenu.shortlink.dto.resp.GroupRespDTO;
import com.haenu.shortlink.service.GroupService;
import com.haenu.shortlink.toolkit.RandomStringGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 新增分组
     *
     * @param groupName 分组名
     */
    @Override
    public void saveGroup(String groupName) {
        String gid;
        while (true) {
            gid = RandomStringGenerator.generateRandomString();
            if (!hasGid(gid)) {
                break;
            }
        }
        GroupDO groupDO = GroupDO.builder()
                .gid(RandomStringGenerator.generateRandomString())
                .sortOrder(0)
                .name(groupName)
                .username(UserContext.getUsername())
                .build();
        save(groupDO);
    }

    /**
     * 查询所有分组
     *
     * @return 所有分组
     */
    @Override
    public List<GroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .isNull(GroupDO::getUsername)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getCreateTime);
        List<GroupDO> groupDOS = list(queryWrapper);
        return BeanUtil.copyToList(groupDOS, GroupRespDTO.class);
    }

    /**
     * 根据gid检查该分组存在
     *
     * @param gid
     * @return
     */

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO groupDO = baseMapper.selectOne(queryWrapper);
        return groupDO != null;
    }
}




