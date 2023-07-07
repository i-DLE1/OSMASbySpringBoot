package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.service.AdminBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/user_notice")
public class User_noticeController {

    private final AdminBoardService adminBoardService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public User_noticeController(AdminBoardService adminBoardService){
        this.adminBoardService = adminBoardService;
    }

    @GetMapping("/notice_view/{boardtype}")
    public String notice_fullview(Model model, @PathVariable String boardtype){
        System.out.println("boardtype = " + boardtype);
        List<AdminBoardDTO> adminBoards = adminBoardService.getAllAdminBoards(boardtype);
        model.addAttribute("adminBoards",adminBoards);
        model.addAttribute("type",boardtype);
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

    // 공지사항 등록 처리 매핑
    @GetMapping("/regist")
    public String goRegister() { return "admin/admin_notice/notice_registration";}

    @PostMapping("/regist")
    public String registBoard(@ModelAttribute AdminBoardDTO board, RedirectAttributes rttr) {
        log.info("");
        log.info("");
        log.info("[BoardController] registBoard =========================================================");
        log.info("[BoardController] registBoard Request : " + board);

        AdminBoardServiceImpl.registBoard(board);

        rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");

        log.info("[BoardController] registBoard =========================================================");

        return "redirect:/board/list";
    }
}



//    @PostMapping("/regist")
//    public String registBoard(@ModelAttribute BoardDTO board, RedirectAttributes rttr) throws BoardRegistException {
//
//        log.info("");
//        log.info("");
//        log.info("[BoardController] registBoard =========================================================");
//        log.info("[BoardController] registBoard Request : " + board);
//
//        boardServiceImpl.registBoard(board);
//
//        rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");
//
//        log.info("[BoardController] registBoard =========================================================");
//
//        return "redirect:/board/list";
//    }



}
