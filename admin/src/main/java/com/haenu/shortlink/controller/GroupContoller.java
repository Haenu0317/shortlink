package com.haenu.shortlink.controller;

import com.haenu.shortlink.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接分组Controller
 * @author haenu
 * @version 1.0
 * @date 2023/12/18 22:19
 */

@RestController
@RequiredArgsConstructor
public class GroupContoller {

    private final GroupService groupService;
}
