package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/notice")
public class notice {

    @GetMapping("notice_article")
    public void notice_article(){}

    @GetMapping("notice_event")
    public void notice_event(){}

    @GetMapping("notice_fullview")
    public void notice_fullview(){}

    @GetMapping("notice_management")
    public void notice_management(){}

    @GetMapping("notice_notice")
    public void notice_notice(){}
}
