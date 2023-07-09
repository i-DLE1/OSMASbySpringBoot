package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.AddressDTO;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.PayServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/member")
public class PayController {

    private final PayServiceImpl payService;
    private final MemberServiceImpl memberService;

    public PayController(PayServiceImpl payService,MemberServiceImpl memberService){

        this.payService = payService;
        this.memberService = memberService;
    }
    @GetMapping("/pay/pay")
    public void goPay(Model m, Principal principal){
        String id = principal.getName();
        MemberDTO member = payService.selectMemberById(id);
        AddressDTO address = payService.selectAddressByNo(member.getNo());

        m.addAttribute("member",member);
        m.addAttribute("address",address);
    }

    @PostMapping("/pay/address")
    @ResponseBody
    public String addAddress(Principal principal,@RequestBody AddressDTO address) throws Exception {
        String id = principal.getName();
        address.setRefMemberNo(memberService.selectNoByNickname(memberService.selectNicknameById(id)));
        return payService.insertAddress(address);
    }
    @PostMapping("/pay/addressMod")
    @ResponseBody
    public String addAddressMod(Principal principal,@RequestBody AddressDTO address) throws Exception {
        System.out.println("===============");
        String id = principal.getName();
        address.setRefMemberNo(memberService.selectNoByNickname(memberService.selectNicknameById(id)));
        return payService.modAddress(address);
    }
}
