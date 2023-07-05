package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.service.SellerApprovalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

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

    @PostMapping("FileUpload")
    public void FileUpload(@RequestParam("file") MultipartFile file) {
        String uploadDirectory = "images/admin/getForm";

        if (!file.isEmpty()) {
            try {
                // 파일 저장 경로 설정
                String filePath = uploadDirectory + "/" + file.getOriginalFilename();

                // 파일을 지정된 경로에 저장
                file.transferTo(new File(filePath));

                // 파일 업로드 성공 처리
                System.out.println("파일 업로드 성공");
            } catch (Exception e) {
                // 파일 업로드 실패 처리
                System.out.println("파일 업로드 실패");
                e.printStackTrace();
            }
        }
    }

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

//    @PostMapping("sellerInsert")
//    public String sellerInsert(@RequestParam Map<String, String> requestParams, Model model) {
//        String sellerId = requestParams.get("sellerId");
//        String bank = requestParams.get("bank");
//        String accountNo = requestParams.get("accountNo");
//        String name = requestParams.get("name");
//        String rprsn = requestParams.get("rprsn");
//        String callNumber = requestParams.get("callNumber");
//        String address = requestParams.get("address");
//        String registNo = requestParams.get("registNo");
//        String reportNo = requestParams.get("reportNo");
//
//        String registFile = requestParams.get("registFile");
//        String reportFile = requestParams.get("reportFile");
//        String certificateFile = requestParams.get("certificateFile");
//        String bankBookFile = requestParams.get("bankBookFile");
//
//        model.addAttribute("sellerId", sellerId);
//        model.addAttribute("bank", bank);
//        model.addAttribute("accountNo", accountNo);
//        model.addAttribute("name", name);
//        model.addAttribute("rprsn", rprsn);
//        model.addAttribute("callNumber", callNumber);
//        model.addAttribute("address", address);
//        model.addAttribute("registNo", registNo);
//        model.addAttribute("reportNo", reportNo);
//
//        model.addAttribute("registFile", registFile);
//        model.addAttribute("reportFile", reportFile);
//        model.addAttribute("certificateFile", certificateFile);
//        model.addAttribute("bankBookFile", bankBookFile);
//
//        int result = sellerApprovalFormService.sellerInsert(requestParams);
//
//        if (result > 0) {
//            return "redirect:/admin/sellerApprovalForm/getForm";
//        } else {
//            return "redirect:/admin/errorPage";
//        }
//    }


}
