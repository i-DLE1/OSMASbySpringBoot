package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/itemApproval")
public class itemApproval {

    @GetMapping("waitingItem")
    public void waitingItem(){}

    @GetMapping("holdingItem")
    public void holdingItem(){}

    @GetMapping("succesItem")
    public void succesItem(){}

}
