package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.ProductDTO;
import com.idle.osmas.admin.service.ItemApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("holdingItem")
    public String holdingItem(Model model) {
        List<ProductDTO> sellerItem = itemApprovalService.holdingAllItem();

        model.addAttribute("sellerItem", sellerItem);

        return "/admin/itemApproval/holdingItem";
    }

    @GetMapping("succesItem")
    public String succesItem(Model model) {
        List<ProductDTO> sellerItem = itemApprovalService.successAllItem();

        model.addAttribute("sellerItem", sellerItem);

        return "/admin/itemApproval/succesItem";
    }

}
