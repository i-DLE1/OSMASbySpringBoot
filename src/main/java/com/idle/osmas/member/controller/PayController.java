package com.idle.osmas.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class PayController {
    @GetMapping("/pay/pay")
    public void goPay(){}

}
