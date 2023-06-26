package com.idle.osmas.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/login/login")
    public void memberLoginForm(){}

    @GetMapping("/signup/signUp")
    public void goSignUp(){}

    @GetMapping("/signup/signUpInfo")
    public void goSignUp2(){}

    @PostMapping("/signup/signUpInfo")
    public String signUpMember(){

        return "redirect:./signUpSuccess";
    }
}
