package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/admin_notice")
public class Admin_noticeController {

    @GetMapping("admin_article")
    public void admin_article(){}

    @GetMapping("admin_event")
    public void admin_event(){}

    @GetMapping("notice_management")
    public void notice_management(){}

    @GetMapping("notice_registration")
    public void notice_registration(){}
}
