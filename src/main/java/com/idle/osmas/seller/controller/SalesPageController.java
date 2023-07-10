package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Console;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/seller")
public class SalesPageController {

    private final SalesService salesService;

    @Autowired
    public SalesPageController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping ("/fragments/product")
    public String SalesInfo(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectOptionByNo(no);
        CategoryDTO categoryDTO = salesService.selectCategoryByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        model.addAttribute("categoryDTO", categoryDTO);
        return "/seller/fragments/product";
    }

    @GetMapping ("/sales/detail")
    public String detail(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectOptionByNo(no);
        CategoryDTO categoryDTO = salesService.selectCategoryByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        model.addAttribute("categoryDTO", categoryDTO);
        return "/seller/sales/detail";
    }

    @GetMapping ("/sales/prjInfo")
    public String prjInfo(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        SellerRollDTO sellerRollDTO = salesService.selectSellerRollByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("sellerRollDTO", sellerRollDTO);
        return "/seller/sales/prjInfo";
    }

    @GetMapping ("/sales/prjQna")
    public String prjQna(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<QnaDTO> qnaList = salesService.selectQnaListByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("qnaList", qnaList);
        return "/seller/sales/prjQna";
    }

    @GetMapping ("/sales/prjFaq")
    public String prjFaq(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<FaqDTO> faqList = salesService.selectFaqListByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("faqList", faqList);
        return "/seller/sales/prjFaq";
    }

    @GetMapping ("/sales/prjNewInfo")
    public String prjNewInfo(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<NewInfoDTO> newInfoList = salesService.selectNewInfoListByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("newInfoList", newInfoList);
        return "/seller/sales/prjNewInfo";
    }


    @GetMapping ("/sales/sellerInfo")
    public String sellerInfo(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        SellerRollDTO sellerRollDTO = salesService.selectSellerRollByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("sellerRollDTO", sellerRollDTO);
        return "/seller/sales/sellerInfo";
    }

    @PostMapping("/sales/prjQna")
    @ResponseBody
    public String registQna(@RequestBody QnaDTO qnaDTO){

//        QnaDTO qnaDTO = new QnaDTO();
//        qnaDTO.setContent(content);
//        model.addAttribute("content", );
//        model.addAttribute("refMemberNo", refMemberNo);
//        model.addAttribute("refPrjNo", refPrjNo);
        System.out.println("qna============================================"+qnaDTO);

        int result = salesService.insertNewQna(qnaDTO);

        if(result > 0){
            return "성공";
        } else{
            return "실패";
        }

    }

}
