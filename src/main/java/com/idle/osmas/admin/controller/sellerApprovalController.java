package com.idle.osmas.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sellerApproval")
public class sellerApprovalController {

    @GetMapping("waitingAuthority")
    public void waitingAuthorization(){}

    @GetMapping("waitingRetrieve")
    public void waitingRetrieve(){}

    @GetMapping("holdingAuthority")
    public void holdingAuthority(){}

    @GetMapping("holdingRetrieve")
    public void holdingRetrieve(){}

    @GetMapping("succesAuthority")
    public void succesAuthority(){}

    @GetMapping("succesRetrieve")
    public void succesRetrieve(){}

}
