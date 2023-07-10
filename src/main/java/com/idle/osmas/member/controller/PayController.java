package com.idle.osmas.member.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idle.osmas.member.dto.*;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.PayServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class PayController {

    private final PayServiceImpl payService;
    private final MemberServiceImpl memberService;

    public PayController(PayServiceImpl payService,MemberServiceImpl memberService){

        this.payService = payService;
        this.memberService = memberService;
    }
//    @GetMapping("/pay/pay")
//    public void goPay(Model m, Principal principal, @RequestParam("no") int no,@RequestParam("productNo") List<Integer> productNo,@RequestParam("count") List<Integer> count){
//        String id = principal.getName();
//        MemberDTO member = payService.selectMemberById(id);
//        AddressDTO address = payService.selectAddressByNo(member.getNo());
//        Long price = 0L;
//        List<ProductsDTO> product = new ArrayList<>();
//        ProductsDTO products;
//        for(int i = 0 ; i < productNo.size();i++){
//            products = payService.selectProduct(productNo.get(i));
//            products.setCount(count.get(i));
//            product.add(products);
//            price += products.getPrice() * products.getCount();
//        }
//        System.out.println(product);
//        PayDTO pay = payService.selectPay(no);
//        pay.setSumPrice(price);
//        m.addAttribute("productList", product);
//        m.addAttribute("pay",pay);
//        m.addAttribute("member",member);
//        m.addAttribute("address",address);
//    }


    @PostMapping("/pay/pay")
    public void goPay(Model m, Principal principal, @RequestParam("no") int no,@RequestParam("productList") List<OptionDTO> productList){

        String id = principal.getName();
        MemberDTO member = payService.selectMemberById(id);
        AddressDTO address = payService.selectAddressByNo(member.getNo());
        Long price = 0L;
        List<ProductsDTO> product = new ArrayList<>();
        ProductsDTO products;
        for(int i = 0 ; i < productList.size();i++){
            products = payService.selectProduct(productList.get(i).getOptionNumber());
            products.setCount(productList.get(i).getOptionAmount());
            product.add(products);
            price += products.getPrice() * products.getCount();
        }
        System.out.println(product);
        PayDTO pay = payService.selectPay(no);
        pay.setSumPrice(price);
        m.addAttribute("productList", product);
        m.addAttribute("pay",pay);
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
