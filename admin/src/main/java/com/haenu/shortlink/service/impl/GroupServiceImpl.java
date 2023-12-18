package com.haenu.shortlink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.dao.entity.GroupDO;
import com.haenu.shortlink.dao.mapper.GroupMapper;
import com.haenu.shortlink.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Haenu0317
 * @description 针对表【t_group】的数据库操作Service实现
 * @createDate 2023-12-18 22:15:26
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO>
        implements GroupService {

}




