package com.idle.osmas.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = {"/", "/admin"})
    public String adminmain () {
        return "admin/adminMain";
    }
}
