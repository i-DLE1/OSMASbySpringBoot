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
    public void formMain() {
    }

    @GetMapping("getForm")
    public void getForm() {
    }

    @GetMapping("outForm")
    public void outForm() {
    }

    @GetMapping("outFormHolding")
    public void outFormHolding() {
    }

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

    @PostMapping("/sellerFileUpload")
    public String sellerFileUpload(
            @RequestParam("sellerId") String sellerId,
            @RequestParam("bank") String bank,
            @RequestParam("accountNo") String accountNo,
            @RequestParam("name") String name,
            @RequestParam("rprsn") String rprsn,
            @RequestParam("callNumber") String callNumber,
            @RequestParam("address") String address,
            @RequestParam("registNo") String registNo,
            @RequestParam("reportNo") String reportNo,
            @RequestParam("registFile") MultipartFile registFile,
            @RequestParam("reportFile") MultipartFile reportFile,
            @RequestParam("certificateFile") MultipartFile certificateFile,
            @RequestParam("bankBookFile") MultipartFile bankBookFile,
            HttpServletRequest request
    ) {
        // 파일 업로드 및 데이터 처리 로직을 구현
        String rootLocation = request.getSession().getServletContext().getRealPath("/resources");
        String fileUploadDirectory = rootLocation + "/static/images/admin/getForm";

        File directory = new File(fileUploadDirectory);

        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }

        //파라미터를 출력
        System.out.println("sellerId: " + sellerId);
        System.out.println("bank: " + bank);
        System.out.println("accountNo: " + accountNo);
        System.out.println("name: " + name);
        System.out.println("rprsn: " + rprsn);
        System.out.println("callNumber: " + callNumber);
        System.out.println("address: " + address);
        System.out.println("registNo: " + registNo);
        System.out.println("reportNo: " + reportNo);

        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("sellerId", sellerId);
        requestParams.put("bank", bank);
        requestParams.put("accountNo", accountNo);
        requestParams.put("name", name);
        requestParams.put("rprsn", rprsn);
        requestParams.put("callNumber", callNumber);
        requestParams.put("address", address);
        requestParams.put("registNo", registNo);
        requestParams.put("reportNo", reportNo);

        // 파일 처리 예시
        if (!registFile.isEmpty()) {
            try {
                String fileName = "registFile"; // 업로드할 파일 이름 설정
                String filePath = fileUploadDirectory + "/" + fileName; // 파일 경로 설정
                File dest = new File(filePath);
                registFile.transferTo(dest); // 파일 업로드
                // 파일 업로드 성공 시 추가적인 처리를 수행할 수 있습니다.
                System.out.println("사업자등록증 사본 업로드 성공");

                requestParams.put("registFileName", fileName);
            } catch (IOException e) {
                // 파일 업로드 실패 시 예외 처리를 수행합니다.
                e.printStackTrace();
                System.out.println("사업자등록증 사본 업로드 실패");
            }
        }
        if (!reportFile.isEmpty()) {
            try {
                String fileName = "reportFile"; // 업로드할 파일 이름 설정
                String filePath = fileUploadDirectory + "/" + fileName; // 파일 경로 설정
                File dest = new File(filePath);
                reportFile.transferTo(dest); // 파일 업로드
                // 파일 업로드 성공 시 추가적인 처리를 수행할 수 있습니다.
                System.out.println("통신판매업 사업자 사본 업로드 성공");

                requestParams.put("reportFileName", fileName);
            } catch (IOException e) {
                // 파일 업로드 실패 시 예외 처리를 수행합니다.
                e.printStackTrace();
                System.out.println("통신판매업 사업자 사본 업로드 실패");
            }
        }
        if (!certificateFile.isEmpty()) {
            try {
                String fileName = "certificateFile"; // 업로드할 파일 이름 설정
                String filePath = fileUploadDirectory + "/" + fileName; // 파일 경로 설정
                File dest = new File(filePath);
                certificateFile.transferTo(dest); // 파일 업로드
                // 파일 업로드 성공 시 추가적인 처리를 수행할 수 있습니다.
                System.out.println("대표자 인감증명서 업로드 성공");

                requestParams.put("certificateFileName", fileName);
            } catch (IOException e) {
                // 파일 업로드 실패 시 예외 처리를 수행합니다.
                e.printStackTrace();
                System.out.println("대표자 인감증명서 업로드 실패");
            }
        }
        if (!bankBookFile.isEmpty()) {
            try {
                String fileName = "bankBookFile"; // 업로드할 파일 이름 설정
                String filePath = fileUploadDirectory + "/" + fileName; // 파일 경로 설정
                File dest = new File(filePath);
                bankBookFile.transferTo(dest); // 파일 업로드
                // 파일 업로드 성공 시 추가적인 처리를 수행할 수 있습니다.
                System.out.println("대표자 혹은 사업자 명의 통장 사본 업로드 성공");

                requestParams.put("bankBookFileName", fileName);
            } catch (IOException e) {
                // 파일 업로드 실패 시 예외 처리를 수행합니다.
                e.printStackTrace();
                System.out.println("대표자 혹은 사업자 명의 통장 사본 업로드 실패");
            }
        }

        int result = sellerApprovalFormService.sellerInsert(requestParams);

        if (result == 1) {
            return "redirect:/admin/sellerApprovalForm/getForm";
        } else {
            return "redirect:/admin/errorPage";
        }
    }
}