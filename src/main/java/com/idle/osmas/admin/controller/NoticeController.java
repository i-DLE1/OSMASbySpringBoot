package com.idle.osmas.admin.controller;


import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.service.AdminBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/admin_notice")
public class NoticeController {

    private final AdminBoardService adminBoardService;
    @Autowired
    public NoticeController(AdminBoardService adminBoardService) {
        this.adminBoardService = adminBoardService;
    }

    @GetMapping("/notice_registration")
    public String goRegister() { return "admin/admin_notice/notice_registration";}

    @PostMapping("/notice_registration")
    @ResponseBody
    public String noticeRegist(@RequestBody AdminBoardDTO adminBoard) {
            int result = adminBoardService.registBoard(adminBoard);
            System.out.println("admin============================================" + adminBoard);
            if (result > 0) {
                return "성공";
            } else {
                return "실패";
            }
        }
}
