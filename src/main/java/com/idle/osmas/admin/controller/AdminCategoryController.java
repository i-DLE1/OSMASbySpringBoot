package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.SellerRoleDTO;
import com.idle.osmas.admin.service.GettingProposalsService;
import com.idle.osmas.member.dto.SuggestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/adminCategory")
public class AdminCategoryController {

    private final GettingProposalsService gettingProposalsService;

    @Autowired
    public AdminCategoryController(GettingProposalsService gettingProposalsService) {
        this.gettingProposalsService = gettingProposalsService;
    }

    @GetMapping("fundingRanking")
    public void fundingRanking(){}

    @GetMapping("gettingProposals")
    public String gettingProposals(Model model){
        List<SuggestDTO> proposalAll = gettingProposalsService.gettingProposalsAll();

        model.addAttribute("proposalAll", proposalAll);

        return "/admin/adminCategory/gettingProposals";
    }
}
