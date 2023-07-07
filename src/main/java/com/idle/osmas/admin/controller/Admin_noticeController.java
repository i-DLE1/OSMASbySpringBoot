//package com.idle.osmas.admin.controller;
//
//import com.idle.osmas.admin.service.AdminBoardService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("admin/admin_notice")
//public class Admin_noticeController {
//    private final AdminBoardService adminBoardService;
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    public Admin_noticeController(AdminBoardService adminBoardService) {
//        this.adminBoardService = adminBoardService;
//    }
//
//    @PostMapping("/notice_registration")
//    public String noticeRegist(@RequestBody AdminBoardDTO adminBoard) {
//        int result = adminBoardService.registBoard(adminBoard);
//        System.out.println("admin============================================"+adminBoard);
//        if (result > 0) {
//            return "성공";
//        } else {
//            return "실패";
//        }
//    }
//}
