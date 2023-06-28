package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/question")
public class QuestionController {

    @GetMapping("advice")
    public void advice(){}

    @GetMapping("handle")
    public void handle(){}

}