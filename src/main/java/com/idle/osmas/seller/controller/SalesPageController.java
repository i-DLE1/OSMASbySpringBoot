package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.OptionDTO;
import com.idle.osmas.seller.dto.SalesDTO;
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

    @GetMapping ("/fragments/product")
    public String SalesInfo(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectAllOption();
        CategoryDTO categoryDTO = salesService.selectCategorynameByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        model.addAttribute("categoryDTO", categoryDTO);
        return "/seller/fragments/product";
    }

    @GetMapping ("/sales/salesPage1")
    public String salesPage1(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectAllOption();
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        return "/seller/sales/salesPage1";
    }

    @GetMapping ("/sales/salesPage2")
    public String salesPage2(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectAllOption();
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        return "/seller/sales/salesPage2";
    }

    @GetMapping ("/sales/salesPage3")
    public String salesPage3(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectAllOption();
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        return "/seller/sales/salesPage3";
    }

    @GetMapping ("/sales/salesPage4")
    public String salesPage4(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectAllOption();
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        return "/seller/sales/salesPage4";
    }

    @GetMapping ("/sales/salesPage5")
    public String salesPage5(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectAllOption();
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        return "/seller/sales/salesPage5";
    }

    @GetMapping ("/sales/salesPage6")
    public String salesPage6(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        List<OptionDTO> optionList = salesService.selectAllOption();
        model.addAttribute("salesDTO", salesDTO);
        model.addAttribute("optionList", optionList);
        return "/seller/sales/salesPage6";
    }


}
