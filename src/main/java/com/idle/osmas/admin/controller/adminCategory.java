package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/adminCategory")
public class adminCategory {

    @GetMapping("fundingRanking")
    public void fundingRanking(){}
}
