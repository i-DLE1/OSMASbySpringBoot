package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SalesPageController {

    private final SalesService salesService;

    @Autowired
    public SalesPageController(SalesService salesService) {
        this.salesService = salesService;
    }

//    @GetMapping ("/fragments/product")
//    public String SalesInfo(Model model, @RequestParam("no") int no){
//        SalesDTO salesDTO = salesService.selectProjectByNo(no);
//        List<OptionDTO> optionList = salesService.selectOptionByNo(no);
//        CategoryDTO categoryDTO = salesService.selectCategoryByNo(no);
//        model.addAttribute("salesDTO", salesDTO);
//        model.addAttribute("optionList", optionList);
//        model.addAttribute("categoryDTO", categoryDTO);
//        return "/seller/fragments/product";
//    }

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
        model.addAttribute("salesDTO", salesDTO);
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

}
