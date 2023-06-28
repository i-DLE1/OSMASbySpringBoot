package com.idle.osmas.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
//@RequestMapping("/admin")
public class adminMain {

    @GetMapping(value = {"/", "admin"})
    public String adminMain () {

        return "admin/adminMain";
    }
}
