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
    public SellerApprovalController(SellerRoleService sellerRoleService){
        this.sellerRoleService = sellerRoleService;
    }

    //권한 코드를 1만 가지고 있는 사람 조회
    @GetMapping("waitingAuthority")
    public String sellerAllApply(Model model){
        List<SellerRoleDTO> sellerApply = sellerRoleService.selectAllApplyRole();

        model.addAttribute("sellerApply", sellerApply);

        return "/admin/sellerApproval/waitingAuthority";
    }

    @GetMapping("waitingRetrieve")
    public String waitingRetrieve(Model model){
        List<SellerRoleDTO> sellerApplyRetrieve = sellerRoleService.selectApplyRoleRetrieve();

        model.addAttribute("sellerApplyRetrieve", sellerApplyRetrieve);

        return "/admin/sellerApproval/waitingRetrieve";

    }

    @GetMapping("holdingAuthority")
    public String holdingAuthority(Model model){
        List<SellerRoleDTO> sellerHolding = sellerRoleService.selectAllHoldingRole();

        model.addAttribute("sellerHolding", sellerHolding);

        return "/admin/sellerApproval/holdingAuthority";
    }

    @GetMapping("holdingRetrieve")
    public String holdingRetrieve(Model model){
        List<SellerRoleDTO> HoldingRetrieve = sellerRoleService.selectHoldingRoleRetrieve();

        model.addAttribute("HoldingRetrieve", HoldingRetrieve);

        return "/admin/sellerApproval/holdingRetrieve";
    }

    @GetMapping("succesAuthority")
    public String sellerAllRole(Model model){
        List<SellerRoleDTO> sellerAll = sellerRoleService.sellerAllRole();

        model.addAttribute("sellerAll", sellerAll);

        return "/admin/sellerApproval/succesAuthority";
    }

    @GetMapping("succesRetrieve")
    public String succesRetrieve(Model model){
        List<SellerRoleDTO> successRetrieve = sellerRoleService.selectSuccessRoleRetrieve();

        model.addAttribute("successRetrieve", successRetrieve);

        return "/admin/sellerApproval/succesRetrieve";
    }

    @PostMapping("grantPermission")
    public String grantPermission(@RequestParam("sellerId") String sellerId) {
        // 판매자 아이디를 기반으로 권한 부여 작업 수행
        // 예시: TBL_ROLE_LIST의 REF_MEMBER_ROLE_CODE에 2 추가, PERMISSION_STATUS를 APPROVAL로 업데이트

        // 예시: 판매자 아이디로 판매자 정보 조회
        SellerRoleDTO seller = sellerRoleService.selectSellerBySellerId(sellerId);

        //TBL_ROLE_LIST에 권한 추가 + TBL_PERMISSION_ROLE의 PERMISSION_STATUS 값 변경
        int result = sellerRoleService.addRoleToList(seller.getMemberNo(), 2);

        int updateResult = sellerRoleService.updatePermissionStatus(seller.getMemberNo(), "APPROVAL");

        // 권한 부여 성공 여부에 따라 리다이렉트 또는 에러 페이지 반환
        if (result > 0 && updateResult > 0) {
            return "redirect:/admin/sellerApproval/successAuthority";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

}
