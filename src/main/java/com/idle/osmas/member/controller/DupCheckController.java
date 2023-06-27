package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/member")
public class DupCheckController {
    @PostMapping("/idDupCheck")
    public void checkDUplication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("id"));



    }
}
