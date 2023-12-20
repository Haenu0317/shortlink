package com.haenu.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.shortlink.project.dao.entity.LinkDO;
import com.haenu.shortlink.project.service.LinkService;
import com.haenu.shortlink.project.dao.mapper.LinkMapper;
import org.springframework.stereotype.Service;

/**
* @author Haenu0317
* @description 针对表【t_link】的数据库操作Service实现
* @createDate 2023-12-20 10:57:56
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, LinkDO>
    implements LinkService {

}




