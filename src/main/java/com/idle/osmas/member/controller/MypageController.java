package com.idle.osmas.member.controller;

import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.member.service.MypageService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/member/mypage/*")
public class MypageController {

    private final MypageService mypageService;

    private final UserDetailsService userDetailsService;


    public MypageController(MypageService mypageService, UserDetailsService userDetailsService) {
        this.mypageService = mypageService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("MypageAccount")
    public void mypageAccount(Principal principal, Model model){
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        MemberDTO member = mypageService.selectMemberByNo(user.getNo());

        model.addAttribute("member", member);
    }
    @GetMapping("MypageAccountDelete")
    public void  mypageAccountDelete(){
    }
    @GetMapping("MypageAccountDeleteNext")
    public void mypageAccountDeleteNext(){
    }
    @PostMapping("MypageAccountDeleteNext")
    public void mypageAccountDeleteNextValue(Principal principal, Model model){
        UserImpl user = (UserImpl) userDetailsService.loadUserByUsername(principal.getName());
        System.out.println("user = " + user.getNo());
//        mypageService.updateMemberStatusByNo(, "DROP", "test2");
    }
    @GetMapping("MypageAlarm")
    public void mypageAlarm(){}
    @GetMapping("MypageMain")
    public void mypageMain(){}
    @GetMapping("MypageMessage")
    public void mypageMessage(){}

    @GetMapping("MypageProfile")
    public void mypageProfile(Principal principal, Model model) throws AccessAuthorityException {
        if (principal == null) throw new AccessAuthorityException("e");
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        MemberDTO member = mypageService.selectMemberByNo(user.getNo());

        model.addAttribute("member", member);
    }

    @PostMapping("MypageProfile")
    @ResponseBody
    public String registProfile(@RequestParam Map<String, String> newProfile, Principal principal){
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        System.out.println("newProfile = " + newProfile);
        int result = mypageService.updateMemberbyNo(user.getNo(),newProfile.get("inputId"),newProfile.get("inputVal"));
        System.out.println("result = " + result);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("MypageShippingAd")
    public void mypageShippingAd(){}
    @GetMapping("MypageSponsor")
    public void mypageSponsor(){}
}
