package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.ProductDTO;
import com.idle.osmas.admin.dto.TermsDTO;
import com.idle.osmas.admin.service.TermsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/footerCategory")
public class FooterCategoryController {

    private final TermsService termsService;

    public FooterCategoryController(TermsService termsService) {
        this.termsService = termsService;
    }

    @GetMapping("OSAMSIntroduction")
    public String OSAMSIntroduction(Model model) {
        List<TermsDTO> OSAMS = termsService.OSAMS();

        model.addAttribute("OSAMS", OSAMS);

        return "/admin/footerCategory/OSAMSIntroduction";
    }

    @GetMapping(value = "/useTerms")
    public String useTerms(Model model) {
        List<TermsDTO> useTerms = termsService.userTermsGet();

        model.addAttribute("useTerms", useTerms);

        return "/admin/footerCategory/useTerms";
    }

    @GetMapping(value = "/personalInformationProcessingPolicy")
    public String personalInformationProcessingPolicy(Model model) {
        List<TermsDTO> personalTerms = termsService.personalTermsGet();

        model.addAttribute("personalTerms", personalTerms);

        return "/admin/footerCategory/personalInformationProcessingPolicy";
    }

    @GetMapping(value = "/projectJudgingCriteria")
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

        if (result > 0) {
            return "redirect:/admin/footerCategory/termsInput";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    @PostMapping (value = "/termsEdit")
    public String termsEdit(@RequestParam("content") String content, @RequestParam("title") String title, Model model) {
        model.addAttribute("content", content);
        model.addAttribute("title", title);

        System.out.println(content);
        System.out.println(title);

        return "/admin/footerCategory/termsEdit";
    }

    @PostMapping("termsEditGO")
    public String termsEdit(@RequestParam("title") String title, @RequestParam("content") String content) {

        int result = termsService.termsEditGO(title, content);

        if (result > 0) {
            return "redirect:/admin/footerCategory/termsInput";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    @ExceptionHandler(Throwable.class)
    public String handleException() {
        return "/admin/errorPage"; // 에러 페이지로 리다이렉트 또는 렌더링할 뷰의 이름을 반환합니다.
    }

}
