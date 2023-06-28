package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/user_management")
public class user_managementController {

    @GetMapping("full_inquiry")
    public void full_inquiry(){}

    @GetMapping("seller_inquiry")
    public void seller_inquiry(){}

    @GetMapping("sponsor_inquiry")
    public void sponsor_inquiry(){}

    @GetMapping("withdraw_inquiry")
    public void withdraw_inquiry(){}
}
