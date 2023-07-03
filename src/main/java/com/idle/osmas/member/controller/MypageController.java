package com.idle.osmas.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/mypage/*")
public class MypageController {
    @GetMapping("MypageAccount")
    public void MypageAccount(){}
    @GetMapping("MypageAccountDelete")
    public void  MypageAccountDelete(){}
    @GetMapping("MypageAccountDeleteNext")
    public void MypageAccountDeleteNext(){}
    @GetMapping("MypageAlarm")
    public void MypageAlarm(){}
    @GetMapping("MypageMain")
    public void MypageMain(){}
    @GetMapping("MypageMessage")
    public void MypageMessage(){}
    @GetMapping("MypageProfile")
    public void MypageProfile(){}
    @GetMapping()
    public void MypageShippingAd(){}
    public void MypageSponsor(){}
}
