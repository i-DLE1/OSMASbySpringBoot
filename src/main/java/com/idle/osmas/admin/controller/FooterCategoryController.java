package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.service.TermsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public void useTerms(){}

    @GetMapping("personalInformationProcessingPolicy")
    public void personalInformationProcessingPolicy(){}

    @GetMapping("projectJudgingCriteria")
    public void projectJudgingCriteria(){}


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
