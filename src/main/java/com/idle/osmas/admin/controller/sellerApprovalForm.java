package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sellerApprovalForm")
public class sellerApprovalForm {

    @GetMapping("formMain")
    public void formMain(){}

    @GetMapping("getForm")
    public void getForm(){}

    @GetMapping("outForm")
    public void outForm(){}
}
