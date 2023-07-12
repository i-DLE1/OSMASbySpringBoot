package com.idle.osmas.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/test/test/*")
public class TestController {

    @GetMapping("test")
    public String getTest(@RequestParam(required = false) String error1, Model model){

        model.addAttribute("error1",error1);
        return "/test/test";
    }
    @PostMapping("test")
    public String postTest(Model model, RedirectAttributes rttr){

        rttr.addAttribute("error1","erroer");
        return "/test/test/test";

//        return "";
    }
}
