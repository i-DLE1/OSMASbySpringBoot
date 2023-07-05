package com.idle.osmas.main.controller;

import com.idle.osmas.seller.dto.SalesDTO;
import com.idle.osmas.seller.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final SalesService salesService;

    @Autowired
    public MainController(SalesService salesService) {
        this.salesService = salesService;
    }
    @GetMapping(value = {"/", "/main"})
    public String main(Model model){
        List<SalesDTO> salesList = salesService.selectAllProject();
        model.addAttribute("salesList", salesList);
        return "main/main";
    }

    @PostMapping(value = "/")
    public String redirectMain(){return "redirect:/";}
}
