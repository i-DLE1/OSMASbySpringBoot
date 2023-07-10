package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.*;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.PayServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
public class PayController {

    private final PayServiceImpl payService;
    private final MemberServiceImpl memberService;
    private String tid;

    public PayController(PayServiceImpl payService,MemberServiceImpl memberService){

        this.payService = payService;
        this.memberService = memberService;
    }
    @GetMapping("/pay/pay")
    public void goPay(Model m, Principal principal, @RequestParam("no") int no,@RequestParam("productNo") List<Integer> productNo,@RequestParam("count") List<Integer> count){
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
//    @PostMapping("/pay/pay")
//    public void goPay(Model m, Principal principal, @RequestParam("no") int no,@RequestParam("productList") List<OptionDTO> productList){
//        String id = principal.getName();
//        MemberDTO member = payService.selectMemberById(id);
//        AddressDTO address = payService.selectAddressByNo(member.getNo());
//        Long price = 0L;
//        List<ProductsDTO> product = new ArrayList<>();
//        ProductsDTO products;
//        for(int i = 0 ; i < productList.size();i++){
//            products = payService.selectProduct(productList.get(i).getOptionNumber());
//            products.setCount(productList.get(i).getOptionAmount());
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

//    @PostMapping("/pay/kakao")
//    public @ResponseBody ReadyResponse payReady(@RequestBody PayInfo pay,Principal principal
//    ,Model model){
//        System.out.println(pay);
//        String user_id = principal.getName();
//        ReadyResponse readyResponse = payService.payReady(pay,user_id);
//        model.addAttribute("tid",readyResponse.getTid());
//        tid = readyResponse.getTid();
//
//        model.addAttribute("pay", pay);
//
//        return readyResponse;
//    }
//    @GetMapping("/pay/paysuccess")
//    public String payCompleted(@RequestParam("pg_token") String pgToken, @ModelAttribute("pay") PayInfo pay,Principal principal ,Model model) {
//
//        String user_id = principal.getName();
//        ApproveResponse approveResponse = payService.payApprove(tid, pgToken,pay,user_id);
//
//        // 5. payment 저장
//        //	orderNo, payMathod, 주문명.
//        // - 카카오 페이로 넘겨받은 결재정보값을 저장.
//        // 로직은 생성
//
//        System.out.println("approveResponse ======================== " + approveResponse);
//
//        // orderService.saveOrder(order,payment);
//
//        return "redirect:/";
//    }

}
