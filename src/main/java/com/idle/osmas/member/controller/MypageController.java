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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/member/mypage/*")
public class MypageController {

    private final MypageService mypageService;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final SellerApprovalFormService sellerApprovalFormService;


    public MypageController(MypageService mypageService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, SellerApprovalFormService sellerApprovalFormService) {
        this.mypageService = mypageService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.sellerApprovalFormService = sellerApprovalFormService;
    }

    @GetMapping(value = "MypageAccount")
    public void mypageAccount(@RequestParam(required = false) String confirmPwdError,
                              @RequestParam(required = false) String failPwdError,
                              String changePwdError,
                              Principal principal,
                              Model model){
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        MemberDTO member = mypageService.selectMemberByNo(user.getNo());

        model.addAttribute("member", member);

        if (confirmPwdError != null) {
            model.addAttribute("confirmPwdError", URLDecoder.decode(confirmPwdError));
        }

        if (changePwdError != null) {
            model.addAttribute("changePwdError", URLDecoder.decode(changePwdError));
        }

        if (failPwdError != null) {
            model.addAttribute("failPwdError", URLDecoder.decode(failPwdError));
        }
    }

    @PostMapping("MypageAccount")
//    @ResponseBody
    public String submitAccount(@RequestParam String curPassword,
                                @RequestParam String changePassword,
                                @RequestParam String checkPassword,
                                Principal principal,
                                RedirectAttributes rattr,
                                HttpServletRequest request) throws ServletException {


        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if (!passwordEncoder.matches(curPassword, user.getPwd())){
            rattr.addAttribute("confirmPwdError" , URLEncoder.encode("비밀번호가 일치하지 않습니다."));
            return "redirect:/member/mypage/MypageAccount";
        }

        if (!changePassword.equals(checkPassword)) {
            rattr.addAttribute("changePwdError", URLEncoder.encode("변경할 비밀번호가 일치하지 않습니다."));
            return "redirect:/member/mypage/MypageAccount";
        }

        changePassword = passwordEncoder.encode(changePassword);

        int pwdResult = mypageService.updatePwdStatusByNo(user.getNo(), changePassword);
        if (pwdResult > 0) {
            request.logout();
            return "redirect:/member/mypage/MypageProfile";
        } else {
                rattr.addAttribute("failPwdError", URLEncoder.encode("변경에 실패했습니다."));
                return "redirect:/member/mypage/MypageAccount";
        }
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
//        return "success";
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

        //권한 신청 보류자
        Integer holdingNo = sellerApprovalFormService.holdingNo(userID);
        boolean alert1 = holdingNo != null && holdingNo == 1;

        //권한 회수 보류자
        Integer holdingOut = sellerApprovalFormService.holdingOut(userID);
        boolean alert2 = holdingOut != null && holdingOut == 1;

        //권한 신청 완료자
        Integer sellerGo = sellerApprovalFormService.sellerGo(userID);
        boolean alert3 = sellerGo != null && sellerGo == 1;

        //권한 회수 완료자
        Integer sellerOut = sellerApprovalFormService.sellerOutt(userID);
        boolean alert4 = sellerOut != null && sellerOut == 1;

        model.addAttribute("alert1", alert1);
        model.addAttribute("alert2", alert2);
        model.addAttribute("alert3", alert3);
        model.addAttribute("alert4", alert4);

        System.out.println("권한 신청 보류 값이야? " + alert1);
        System.out.println("권한 신청 성공 값이야? " + alert3);
        System.out.println("권한 회수 보류 값이야? " + alert2);
        System.out.println("권한 회수 성공 값이야? " + alert4);

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
    public void mypageShippingAd(Principal principal, Model model){
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        MemberDTO userJoin = mypageService.selectJoinByNo(user.getNo());
        model.addAttribute("userJoin", userJoin);
        System.out.println("userJoin = " + userJoin);

        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(int i=0; i < memberDTOList.size() ; i++){
            System.out.println("memberDTOList = " + memberDTOList.get(i));
        }
    }

    @PostMapping("MypageShippingAd")
//    @ResponseBody
    public String test(@RequestParam Map<String, String> test, Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        int resultInfo = mypageService.allInfoByNO(user.getNo(), test.get("joinName"), test.get("joinPhone"), test.get("general"), test.get("joinDetail"), test.get("postalCode"));

        if (resultInfo == 1) {
            return "redirect:/";
//            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("MypageSponsor")
    public void mypageSponsor(){}
}
