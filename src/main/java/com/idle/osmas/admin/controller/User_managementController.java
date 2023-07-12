package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.UserManagementDTO;
import com.idle.osmas.admin.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("admin/user_management")
public class User_managementController {

    private final UserManagementService userManagementService;

    @Autowired
    public User_managementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    //    회원 전체 조회
    @GetMapping("/full_inquiry")
    public String full_inquiry(Model model) {
        List<UserManagementDTO> users = userManagementService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user_management/full_inquiry";
    }

    @GetMapping("/seller_inquiry")
    public void seller_inquiry() {
        // Seller inquiry logic
    }

    @GetMapping("sponsor_inquiry")
    public void sponsor_inquiry() {
        // Sponsor inquiry logic
    }

    @GetMapping("withdraw_inquiry")
    public void withdraw_inquiry() {
        // Withdraw inquiry logic
    }
}
