package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/sales")
public class SalesPageController {

    private final SalesService salesService;

    @Autowired
    public SalesPageController(SalesService salesService) {
        this.salesService = salesService;
    }


    @GetMapping("salesPage1")
    public String salesInfo(Model model){
        ProjectDTO projectDTO = salesService.selectProjectByNo(0);
        return "/seller/sales/salesPage1";
    }
    @GetMapping("salesPage2")
    public void salesPage2(){}
    @GetMapping("salesPage3")
    public void salesPage3(){}
    @GetMapping("salesPage4")
    public void salesPage4(){}
    @GetMapping("salesPage5")
    public void salesPage5(){}
    @GetMapping("salesPage6")
    public void salesPage6(){}

}
