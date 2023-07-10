package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.ProductDTO;
import com.idle.osmas.admin.service.ItemApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/itemApproval")
public class ItemApprovalController {

    private final ItemApprovalService itemApprovalService;

    public ItemApprovalController(ItemApprovalService itemApprovalService) {
        this.itemApprovalService = itemApprovalService;
    }

    //상품 신청자 확인
    @GetMapping("waitingItem")
    public String waitingItem(Model model) {
        List<ProductDTO> sellerItem = itemApprovalService.waitingAllItem();

        model.addAttribute("sellerItem", sellerItem);

        return "/admin/itemApproval/waitingItem";
    }

    //상품 승인 보류자
    @GetMapping("holdingItem")
    public String holdingItem(Model model) {
        List<ProductDTO> sellerItem = itemApprovalService.holdingAllItem();

        model.addAttribute("sellerItem", sellerItem);

        return "/admin/itemApproval/holdingItem";
    }

    //상품 승인 완료자
    @GetMapping("succesItem")
    public String succesItem(Model model) {
        List<ProductDTO> sellerItem = itemApprovalService.successAllItem();

        model.addAttribute("sellerItem", sellerItem);

        return "/admin/itemApproval/succesItem";
    }

    //상품 승인 신청 -> 승인 완료
    @GetMapping("endProgress")
    public String endProgress(@RequestParam("sellerId") String sellerId, @RequestParam("projectNo") int projectNo) {

        int result = itemApprovalService.endProgress(sellerId, projectNo);

        if (result > 0) {
            return "redirect:/admin/itemApproval/waitingItem";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    //상품 승인 신청 -> 승인 보류
    @PostMapping("noProgress")
    public String noProgress(@RequestParam("sellerId") String sellerId, @RequestParam("projectNo") int projectNo,
                             @RequestParam("reasonText") String reasonText) {

        int result = itemApprovalService.noProgress(sellerId, projectNo, reasonText);

        if (result > 0) {
            return "redirect:/admin/itemApproval/waitingItem";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

}
