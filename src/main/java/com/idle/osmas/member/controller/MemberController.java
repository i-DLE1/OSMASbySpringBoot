package com.idle.osmas.member.controller;


import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.service.MemberServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public void memberLoginForm(@RequestParam(value = "error",required = false) String error,
                                @RequestParam(value = "exception",required = false) String exception, Model model
                                ){
        model.addAttribute("error",error);
        model.addAttribute("exception" , exception);
    }

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
