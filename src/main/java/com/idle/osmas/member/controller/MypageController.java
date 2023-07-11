package com.idle.osmas.member.controller;

import com.idle.osmas.admin.service.SellerApprovalFormService;
import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.MemberStatus;
import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.member.service.MypageService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/member/mypage/*")
public class MypageController {

    private final MypageService mypageService;

    private final UserDetailsService userDetailsService;

    private final SellerApprovalFormService sellerApprovalFormService;

    public MypageController(MypageService mypageService, UserDetailsService userDetailsService,
                            SellerApprovalFormService sellerApprovalFormService) {
        this.mypageService = mypageService;
        this.userDetailsService = userDetailsService;
        this.sellerApprovalFormService = sellerApprovalFormService;
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
    @ResponseBody
    public String mypageAccountDeleteNextValue(@RequestParam String reason,
                                             Principal principal,
                                             HttpServletRequest request) throws ServletException {
        UserImpl user = (UserImpl) userDetailsService.loadUserByUsername(principal.getName());
        int result = mypageService.updateMemberStatusByNo(user.getNo(), MemberStatus.DROP, reason);
        if (result > 0){
            request.logout();
            return "success";
        } else {
            return "fail";
        }
    }
    @GetMapping("MypageAlarm")
    public String mypageAlarm(Model model){
        //판매자 권한 신청 알람 보류/완료 확인용
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 사용자 아이디 가져오기
        String userID = userDetails.getUsername();

        // 아이디 값을 모델에 추가하여 Thymeleaf 템플릿으로 전달
        model.addAttribute("userID", userID);

        Integer holding = sellerApprovalFormService.youHolding(userID);
        boolean alert = holding != null && holding == 1;

        //권한 회수 완료자
        Integer success = sellerApprovalFormService.youSuccess(userID);
        boolean alertGo = success != null && success == 1;

        model.addAttribute("alert", alert);
        model.addAttribute("alertGo", alertGo);

        System.out.println("권한 신청 보류 값이야? " + alert);
        System.out.println("권한 회수 성공 값이야? " + alertGo);

        return "/member/mypage/MypageAlarm";
    }
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
