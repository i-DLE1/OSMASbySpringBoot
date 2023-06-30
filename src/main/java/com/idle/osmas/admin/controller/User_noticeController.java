package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dao.AdminBoardMapper;
import com.idle.osmas.admin.dto.AdminBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/user_notice")
public class User_noticeController {

    private final AdminBoardMapper adminBoardMapper;

    @Autowired
    public User_noticeController(AdminBoardMapper adminBoardMapper){
        this.adminBoardMapper = adminBoardMapper;
    }

    @GetMapping("notice_article")
    public String notice_article(Model model) {
        String category = "notice_article";
        List<AdminBoardDTO> adminBoards = adminBoardMapper.getAdminBoardsByCategory(category);
        model.addAttribute("adminBoards", adminBoards);

        return "admin/user_notice/notice_article";
    }

    @GetMapping("notice_content")
    public String notice_content(Model model) {
        String category = "notice_content";
        List<AdminBoardDTO> adminBoards = adminBoardMapper.getAdminBoardsByCategory(category);
        model.addAttribute("adminBoards", adminBoards);

        return "admin/user_notice/notice_content";
    }

    @GetMapping("notice_event")
    public String notice_event(Model model) {
        String category = "notice_event";
        List<AdminBoardDTO> adminBoards = adminBoardMapper.getAdminBoardsByCategory(category);
        model.addAttribute("adminBoards", adminBoards);

        return "admin/user_notice/notice_event";
    }

    @GetMapping("notice_fullview")
    public String notice_fullview(Model model){
        List<AdminBoardDTO> adminBoards = adminBoardMapper.getAllAdminBoards();
        model.addAttribute("adminBoards",adminBoards);

        return "admin/user_notice/notice_fullview";


    }


    @GetMapping("notice_notice")
    public String notice_notice(Model model) {
        String category = "notice_notice";
        List<AdminBoardDTO> adminBoards = adminBoardMapper.getAdminBoardsByCategory(category);
        model.addAttribute("adminBoards", adminBoards);

        return "admin/user_notice/notice_notice";
    }
}
