package com.idle.osmas.member.controller;


import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.service.EmailServiceImpl;
import com.idle.osmas.member.service.MemberServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberServiceImpl memberService;

    private final EmailServiceImpl emailService;

    public MemberController(PasswordEncoder passwordEncoder, MemberServiceImpl memberService,
                            EmailServiceImpl emailService){
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
        this.emailService = emailService;
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
    @GetMapping("/signup/signUpSuccess")
    public void goSignUp3(){}
    @PostMapping("/signup/signUpInfo")
    public String signUpMember(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
        String stringBirth =request.getParameter("birthString");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd"); // date 타입으로 변경
        SimpleDateFormat birthFormat = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date utilBirth = null;

        try {
            utilBirth = dateFormat.parse(stringBirth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        stringBirth= birthFormat.format(utilBirth);
        java.sql.Date birth = java.sql.Date.valueOf(stringBirth);

        member.setBirth(birth);
        member.setPwd(passwordEncoder.encode(member.getPwd()));
        memberService.signUpMember(member);
        rttr.addAttribute("nickname",member.getNickname());

        return "redirect:/member/signup/signUpSuccess";
    }

    @GetMapping("/findinfo/findid")
    public void findId(){}
    // email로 id 찾기
    @PostMapping("/findinfo/findid")
    public String findId(@RequestParam("email") String email, Model m) throws MessagingException, UnsupportedEncodingException {
        String result = emailService.selectIdByEmail(email);
        m.addAttribute("result",result);
        return "/member/findinfo/findsuccess";
    }

    @GetMapping("/findinfo/findpwd")
    public void findPwd(){}

    @PostMapping("/findinfo/findpwd")
    public String findPwd(@RequestParam("email") String email, Model m) throws Exception {
        String result = emailService.selectPwdByEmail(email);
        m.addAttribute("result", result);
        return "/member/findinfo/findsuccess";
    }


    @GetMapping("/findinfo/findsuccess")
    public void findsuccess(){}
}
