package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.SellerRoleDTO;
import com.idle.osmas.admin.service.SellerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/sellerApproval")
public class SellerApprovalController {

    private final SellerRoleService sellerRoleService;

    @Autowired
    public SellerApprovalController(SellerRoleService sellerRoleService) {
        this.sellerRoleService = sellerRoleService;
    }

    //권한 신청자 확인
    @GetMapping("waitingAuthority")
    public String sellerAllApply(Model model) {
        List<SellerRoleDTO> sellerApply = sellerRoleService.selectAllApplyRole();

        model.addAttribute("sellerApply", sellerApply);

        return "/admin/sellerApproval/waitingAuthority";
    }

    //권한 회수 신청자 확인
    @GetMapping("waitingRetrieve")
    public String waitingRetrieve(Model model) {
        List<SellerRoleDTO> sellerApplyRetrieve = sellerRoleService.selectApplyRoleRetrieve();

        model.addAttribute("sellerApplyRetrieve", sellerApplyRetrieve);

        return "/admin/sellerApproval/waitingRetrieve";

    }

    //권한 보류자 확인
    @GetMapping("holdingAuthority")
    public String holdingAuthority(Model model) {
        List<SellerRoleDTO> sellerHolding = sellerRoleService.selectAllHoldingRole();

        model.addAttribute("sellerHolding", sellerHolding);

        return "/admin/sellerApproval/holdingAuthority";
    }

    //권한 회수 보류자 확인
    @GetMapping("holdingRetrieve")
    public String holdingRetrieve(Model model) {
        List<SellerRoleDTO> HoldingRetrieve = sellerRoleService.selectHoldingRoleRetrieve();

        model.addAttribute("HoldingRetrieve", HoldingRetrieve);

        return "/admin/sellerApproval/holdingRetrieve";
    }

    //권한 완료자 확인(모든 판매자)
    @GetMapping("succesAuthority")
    public String sellerAllRole(Model model) {
        List<SellerRoleDTO> sellerAll = sellerRoleService.sellerAllRole();

        model.addAttribute("sellerAll", sellerAll);

        return "/admin/sellerApproval/succesAuthority";
    }

    //권한 회수 완료자 확인
    @GetMapping("succesRetrieve")
    public String succesRetrieve(Model model) {
        List<SellerRoleDTO> successRetrieve = sellerRoleService.selectSuccessRoleRetrieve();

        model.addAttribute("successRetrieve", successRetrieve);

        return "/admin/sellerApproval/succesRetrieve";
    }

    //권한 신청 -> 완료
    @PostMapping("grantPermission")
    public String grantPermission(@RequestParam("sellerId") String sellerId, Model model) {
        model.addAttribute("sellerId", sellerId);

        int result = sellerRoleService.grant(sellerId);

        if (result > 0) {
            return "redirect:/admin/sellerApproval/waitingAuthority";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    //권한 회수신청 -> 완료
    @PostMapping("dropPermission")
    public String dropPermission(@RequestParam("sellerId") String sellerId,
                                 Model model) {
        model.addAttribute("sellerId", sellerId);

        int result = sellerRoleService.drop(sellerId);

        if (result > 0) {
            return "redirect:/admin/sellerApproval/waitingRetrieve";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    //권한 신청 -> 보류
    @PostMapping("holdingPermission")
    public String holdingPermission(@RequestParam("sellerId") String sellerId, @RequestParam("reason") String reason,
                                    @RequestParam("sellerReq") int sellerReq, Model model) {
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("reason", reason);
        model.addAttribute("sellerReq", sellerReq);

        int result = sellerRoleService.holdingGrant(sellerId, reason, sellerReq);

        if (result > 0) {
            return "redirect:/admin/sellerApproval/waitingAuthority";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    //권한 회수 신청 ->보류
    @PostMapping("holdingRetrieveGo")
    public String holdingRetrieveGo(@RequestParam("sellerId") String sellerId, @RequestParam("reason") String reason,
                                    @RequestParam("sellerReq") int sellerReq,@RequestParam("sellerNo") int sellerNo,
                                    Model model) {
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("reason", reason);
        model.addAttribute("sellerReq", sellerReq);

        int result = sellerRoleService.holdingRetrieveGo(sellerId, reason, sellerReq, sellerNo);

        if (result > 0) {
            return "redirect:/admin/sellerApproval/waitingRetrieve";
        } else {
            return "redirect:/admin/errorPage";
        }
    }
}
