package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.ProductDTO;
import com.idle.osmas.admin.dto.TermsDTO;
import com.idle.osmas.admin.service.TermsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/footerCategory")
public class FooterCategoryController {

    private final TermsService termsService;

    public FooterCategoryController(TermsService termsService) {
        this.termsService = termsService;
    }

    @GetMapping("OSAMSIntroduction")
    public void OSAMSIntroduction(){}

    @GetMapping("useTerms")
    public String useTerms(Model model) {
        List<TermsDTO> useTerms = termsService.userTermsGet();

        model.addAttribute("useTerms", useTerms);

        return "/admin/footerCategory/useTerms";
    }

    @GetMapping("personalInformationProcessingPolicy")
    public String personalInformationProcessingPolicy(Model model) {
        List<TermsDTO> personalTerms = termsService.personalTermsGet();

        model.addAttribute("personalTerms", personalTerms);

        return "/admin/footerCategory/personalInformationProcessingPolicy";
    }

    @GetMapping("projectJudgingCriteria")
    public String projectJudgingCriteria(Model model) {
        List<TermsDTO> projectTerms = termsService.projectTermsGet();

        model.addAttribute("projectTerms", projectTerms);

        return "/admin/footerCategory/projectJudgingCriteria";
    }


    @GetMapping("termsInput")
    public String termsInput() {
        return "/admin/footerCategory/termsInput";
    }

    @PostMapping("termsInputGo")
    public String termsInputGo(@RequestParam("title") String title, @RequestParam("content") String content) {

        int result = termsService.termsInputGo(title, content);
        return "/admin/footerCategory/termsInput";
    }
}
