package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/user_noticeController")
public class User_noticeController {

    @GetMapping("notice_article")
    public void notice_article(){}

    @GetMapping("notice_content")
    public void notice_content(){}

    @GetMapping("notice_event")
    public void notice_event(){}

    @GetMapping("notice_fullview")
    public void notice_fullview(){}

    @GetMapping("notice_notice")
    public void notice_notice(){}
}
