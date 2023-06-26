package com.idle.osmas.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/sales")
public class SalesPageController {
    @GetMapping("salesPage1")
    public void salesPage1(){}
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
