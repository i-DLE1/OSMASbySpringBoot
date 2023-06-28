package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.service.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/member")
public class DupCheckController {

    private final MemberServiceImpl memberService;

    public DupCheckController(MemberServiceImpl memberService){
        this.memberService = memberService;
    }
    @PostMapping("/idDupCheck")
    @ResponseBody
    public String checkDUplication(@RequestParam("id") String id){
       String result = "사용 가능한 아이디입니다";
        System.out.println(id);
        if("".equals(id)){
            result = "아이디를 입력 해주세요.";
        } else if(memberService.selectMemberById(id)){
            result = "중복된 아이디가 있습니다";
        }
        return result;
    }
    @PostMapping("/nickDupCheck")
    @ResponseBody
    public String checkNickDUplication(@RequestParam("nickname") String nickname){
        String result = "사용 가능한 닉네임입니다";
        System.out.println(nickname);
        if("".equals(nickname)){
            result = "닉네임를 입력 해주세요.";
        } else if(memberService.selectMemberByNickname(nickname)){
            result = "중복된 닉네임 있습니다";
        }
        return result;
    }
}
