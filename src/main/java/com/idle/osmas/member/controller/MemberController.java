package com.idle.osmas.member.controller;


import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.service.MemberServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberServiceImpl memberService;

    public MemberController(PasswordEncoder passwordEncoder, MemberServiceImpl memberService){
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
    }
    @GetMapping("/login/login")
    public void memberLoginForm(){}

    @GetMapping("/signup/signUp")
    public void goSignUp(){}

    @GetMapping("/signup/signUpInfo")
    public void goSignUp2(){}

    @PostMapping("/signup/signUpInfo")
    public String signUpMember(@ModelAttribute MemberDTO member){

        member.setPwd(passwordEncoder.encode(member.getPwd()));

        memberService.signUpMember(member);
        return "redirect:./signUpSuccess";
    }
}
