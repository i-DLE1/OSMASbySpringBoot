package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.service.AdminBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
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
        return "admin/admin_notice/notice_content"; // 공지사항 내용을 보여주는 페이지로 이동합니다.
    }


    // 게시글 등록 메소드
    @PostMapping("/admin/admin_notice/notice_registration")
    public String noticeRegist(@ModelAttribute("adminBoard") AdminBoardDTO adminBoard, Model model) {
        int result = adminBoardService.registBoard(adminBoard);
        System.out.println("admin============================================" + adminBoard);

        return "redirect:/admin/admin_notice/notice_fullview";
    }

    // 공지사항 삭제 메소드
    @PostMapping("/delete")
    public String deleteNotice(@RequestParam int no) {
        System.out.println("no = " + no);
        adminBoardService.deleteAdminBoard(no);
        return "redirect:/admin/user_notice/notice_view/A"; // 경로 수정

    }

    @GetMapping("/notice_edit")
    public String noticeEdit(@RequestParam(required = false) Integer no, Principal principal, Model model){

        AdminBoardDTO existingNotice = adminBoardService.getAdminBoardByNo(no);

        // 수정된 데이터를 View로 전달
        model.addAttribute("no", existingNotice.getNo());
        model.addAttribute("title", existingNotice.getTitle());
        model.addAttribute("content", existingNotice.getContent());

        return "/admin/admin_notice/noticeEdit";
    }


    @PostMapping("/notice_edit")
    public String postNoticeEdit(@RequestParam(required = false) Map<String, Object> adminBoard, Principal principal, Model model){

//        System.out.println("adminBoard = " + adminBoard);
//        int no = Integer.parseInt(adminBoard.get("no"));
//        String title = adminBoard.get("title");
//        String content = adminBoard.get("content");

//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("no", no);
//        paramMap.put("title", title);
//        paramMap.put("content", content);

        adminBoardService.updateAdminBoard(adminBoard);

        return "redirect:/admin/user_notice/notice_view/A";
    }


}