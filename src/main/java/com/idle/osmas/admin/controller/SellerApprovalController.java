package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.SellerRoleDTO;
import com.idle.osmas.admin.service.SellerRoleService;
import com.idle.osmas.admin.service.SellerRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/sellerApproval")
public class SellerApprovalController {

    private final SellerRoleService sellerRoleService;

    @Autowired
    public SellerApprovalController(SellerRoleService sellerRoleService){
        this.sellerRoleService = sellerRoleService;
    }
    @GetMapping("waitingAuthority")
    public String sellerAllApply(Model model){
        List<SellerRoleDTO> sellerApply = sellerRoleService.selectAllsellerRole();

        model.addAttribute("sellerApply", sellerApply);

        return "/admin/sellerApproval/waitingAuthority";
    }

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
