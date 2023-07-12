package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.*;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.PayServiceImpl;
import com.idle.osmas.seller.dto.ProjectFileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public void goPay(Model m,Principal principal, @RequestParam("no") int no,@RequestParam("productNo") int[] productNo,@RequestParam("count") int[] count){
        System.out.println("===============");
        System.out.println(no);
        System.out.println(productNo[0]);

        String id = principal.getName();


        MemberDTO member = payService.selectMemberById(id);
        AddressDTO address = payService.selectAddressByNo(member.getNo());


        Long price = 0L;
        List<ProductsDTO> product = new ArrayList<>();
        ProductsDTO products;

        for(int i = 0 ; i < productNo.length;i++){
            products = payService.selectProduct(productNo[i]);
            products.setCount(count[i]);
            product.add(products);
            price += products.getPrice() * products.getCount();
        }
        System.out.println(product);
        PayDTO pay = payService.selectPay(no);
        pay.setSumPrice(price);
        ProjectFileDTO projectFile = payService.selectProjectFile(no); // 프로젝트no로 썸네일 파일 가져옴
        System.out.println(projectFile);
        m.addAttribute("projectFile",projectFile);
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

    @PostMapping("/pay/kakao")
    public @ResponseBody ReadyResponse payReady(HttpServletRequest request, @RequestBody PayInfo pay, Principal principal
    , Model model){
        HttpSession session = request.getSession();
        System.out.println(pay);
        String user_id = principal.getName();
        ReadyResponse readyResponse = payService.payReady(pay,user_id);
        model.addAttribute("tid",readyResponse.getTid());
        tid = readyResponse.getTid();
        System.out.println("pay=========kakao" + pay);
        session.setAttribute("pay",pay);
        return readyResponse;
    }
    @GetMapping("/paysuccess")
    public String payCompleted(HttpServletRequest request,@RequestParam("pg_token") String pgToken,Principal principal) throws Exception {
        PayInfo pay = (PayInfo) request.getSession().getAttribute("pay");
        System.out.println("==========");
        System.out.println("pg_token :" +pgToken);
        System.out.println("pay ====" + pay);
        String user_id = principal.getName();
        ApproveResponse approveResponse = payService.payApprove(tid, pgToken,pay,user_id);



        System.out.println("approveResponse ======================== " + approveResponse);
        if(payService.paySuccess(pay,user_id)) {
            request.getSession().removeAttribute("pay");
            return "redirect:/member/pay/paysuccess";
        }else{
            throw new Exception("고객센터에 문의 바랍니다.");
        }

    }
    @GetMapping("/pay/paysuccess")
    public void goSuccess(){}
    @GetMapping("/pay/paycancel")
    public void goCancel(){}
    @GetMapping("/pay/payfail")
    public void goFail(){}

    @GetMapping("/paycancel")
    public String payCancel(){
        return "redirect:/member/pay/paycancel";
    }
    @GetMapping("/payfail")
    public String payFail(){
        return "redirect:/member/pay/payfail";
    }

}
