package com.haenu.shortlink.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.shortlink.dao.entity.GroupDO;
import com.haenu.shortlink.dto.req.GroupSortReqDTO;
import com.haenu.shortlink.dto.req.GroupUpdateReqDTO;
import com.haenu.shortlink.dto.resp.GroupRespDTO;

import java.util.List;

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

    /**
     * 查询所有分组
     * @return 所有分组
     */
    List<GroupRespDTO> listGroup();

    /**
     * 修改分组信息
     * @param requestParm
     */
    void updateGroup(GroupUpdateReqDTO requestParm);

    /**
     * 删除分组信息
     * @param requestParm
     */
    void deleteGroup(String requestParm);

    /**
     * 短链接分组排序
     * @param requestParm
     */
    void sortGroup(List<GroupSortReqDTO> requestParm);
}
