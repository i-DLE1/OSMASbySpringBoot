package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.AddressDTO;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.service.PayServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/member")
public class PayController {

    private final PayServiceImpl payService;

    public PayController(PayServiceImpl payService){
        this.payService = payService;
    }
    @GetMapping("/pay/pay")
    public void goPay(Model m, Principal principal){
        String id = principal.getName();
        MemberDTO member = payService.selectMemberById(id);
        AddressDTO address = payService.selectAddressByNo(member.getNo());

        m.addAttribute("member",member);
        m.addAttribute("address",address);
    }

}
