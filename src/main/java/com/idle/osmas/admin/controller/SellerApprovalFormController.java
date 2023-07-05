package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.service.SellerApprovalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin/sellerApprovalForm")
public class SellerApprovalFormController {

    private final SellerApprovalFormService sellerApprovalFormService;

    @Autowired
    public SellerApprovalFormController(SellerApprovalFormService sellerApprovalFormService) {
        this.sellerApprovalFormService = sellerApprovalFormService;
    }

    @GetMapping("formMain")
    public void formMain(){}

    @GetMapping("getForm")
    public void getForm(){}

    @GetMapping("outForm")
    public void outForm(){}

    @GetMapping("outFormHolding")
    public void outFormHolding(){}

    @PostMapping("sellerOut")
    public String sellerOut(@RequestParam Map<String, String> requestParams, Model model) {
        String reasonSelect = requestParams.get("reasonSelect");
        String otherReasonInput = requestParams.get("otherReasonInput");
        String sellerId = requestParams.get("sellerId");

        model.addAttribute("reasonSelect", reasonSelect);
        model.addAttribute("otherReasonInput", otherReasonInput);
        model.addAttribute("sellerId", sellerId);

        int result = sellerApprovalFormService.sellerOut(requestParams);

        if (result > 0) {
            return "redirect:/admin/sellerApprovalForm/outForm";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    @PostMapping("sellerOutCancel")
    public String sellerOutCancel(@RequestParam("sellerId") String sellerId, Model model) {

        model.addAttribute("sellerId", sellerId);

        int result = sellerApprovalFormService.sellerOutCancel(sellerId);

        if (result > 0) {
            return "redirect:/admin/sellerApprovalForm/outForm";
        } else {
            return "redirect:/admin/errorPage";
        }
    }


    @PostMapping("sellerInsert")
    public String sellerInsert(Map<String, String> requestParams) {


            int result = sellerApprovalFormService.sellerInsert(requestParams);

            if (result > 0) {
                return "redirect:/admin/sellerApprovalForm/getForm";
            } else {
                return "redirect:/admin/errorPage";
            }
    }
}
