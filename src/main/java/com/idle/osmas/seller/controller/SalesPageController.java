package com.idle.osmas.seller.controller;

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
        model.addAttribute("salesDTO", salesDTO);
        return "/seller/fragments/product";
    }

    @GetMapping ("/sales/salesPage1")
    public String salesPage1(Model model, @RequestParam("no") int no){
        SalesDTO salesDTO = salesService.selectProjectByNo(no);
        model.addAttribute("salesDTO", salesDTO);
        return "/seller/sales/salesPage1";
    }

//    @GetMapping("/sales/salesPage1")
//    public String salesPage1(Model model){
//        List<SalesDTO> salesList = salesService.selectAllProject();
//        model.addAttribute("salesList", salesList);
//        return "/seller/sales/salesPage1";
//    }
    @GetMapping("/sales/salesPage2")
    public void salesPage2(){}
    @GetMapping("/sales/salesPage3")
    public void salesPage3(){}
    @GetMapping("/sales/alesPage4")
    public void salesPage4(){}
    @GetMapping("/sales/salesPage5")
    public void salesPage5(){}
    @GetMapping("/sales/salesPage6")
    public void salesPage6(){}

}
