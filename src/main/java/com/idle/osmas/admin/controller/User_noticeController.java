package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.service.AdminBoardService;
import com.idle.osmas.seller.dto.QnaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user_notice")
public class User_noticeController {

    private final AdminBoardService adminBoardService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public User_noticeController(AdminBoardService adminBoardService) {
        this.adminBoardService = adminBoardService;
    }

    @GetMapping("/notice_view/{boardtype}")
    public String notice_fullview(Model model, @PathVariable String boardtype) {
        System.out.println("boardtype = " + boardtype);
        List<AdminBoardDTO> adminBoards = adminBoardService.getAllAdminBoards(boardtype);
        model.addAttribute("adminBoards", adminBoards);
        model.addAttribute("type", boardtype);
        return "/admin/admin_notice/notice_fullview";
    }

    @GetMapping("/notice_content")
    public String notice_content(@RequestParam("no") int no, Model model) {
        // no에 해당하는 공지사항의 content를 조회합니다.

        AdminBoardDTO noticeContent = adminBoardService.getAdminBoardByNo(no);
        System.out.println("noticeContent =========== " + noticeContent);
        model.addAttribute("noticeContent", noticeContent);
        return "/admin/user_notice/notice_content"; // 공지사항 내용을 보여주는 페이지로 이동합니다.
    }


    @PostMapping("/admin/admin_notice/notice_registration")
    public String noticeRegist(@ModelAttribute("adminBoard") AdminBoardDTO adminBoard, Model model) {
        int result = adminBoardService.registBoard(adminBoard);
        System.out.println("admin============================================" + adminBoard);

        return "redirect:/admin/admin_notice/notice_fullview";
    }



}
