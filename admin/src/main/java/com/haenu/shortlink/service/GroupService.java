package com.haenu.shortlink.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.shortlink.dao.entity.GroupDO;

/**
 * @author Haenu0317
 * @description 针对表【t_group】的数据库操作Service
 * @createDate 2023-12-18 22:15:26
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增分组
     * @param groupName 分组名
     */
    void saveGroup(String groupName);
}
