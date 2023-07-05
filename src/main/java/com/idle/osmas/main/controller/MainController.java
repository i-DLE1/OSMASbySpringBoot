package com.idle.osmas.main.controller;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.service.AdminBoardService;
import com.idle.osmas.seller.dto.SalesDTO;
import com.idle.osmas.seller.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final SalesService salesService;
    private final AdminBoardService adminBoardService;

    @Autowired
    public MainController(SalesService salesService, AdminBoardService adminBoardService) {
        this.salesService = salesService;

        this.adminBoardService = adminBoardService;
    }
    @GetMapping(value = {"/", "/main"})
    public String main(Model model){
        List<SalesDTO> salesList = salesService.selectAllProject();
        model.addAttribute("salesList", salesList);
        return "main/main";
    }

    @PostMapping(value = "/")
    public String redirectMain(){return "redirect:/";}

    @GetMapping("/notice_view/{boardtype}")
    public String notice_fullview(Model model, @PathVariable String boardtype){
        System.out.println("boardtype = " + boardtype);
        List<AdminBoardDTO> adminBoards = adminBoardService.getAllAdminBoards(boardtype);
        model.addAttribute("adminBoards",adminBoards);
        return "/admin/user_notice/notice_fullview";
    }

}
