package com.idle.osmas.admin.controller;

import com.idle.osmas.admin.dto.SellerRoleDTO;
import com.idle.osmas.admin.service.SellerApprovalFormService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin/sellerApprovalForm")
public class SellerApprovalFormController {

    private final SellerApprovalFormService sellerApprovalFormService;
    private final String SAVE_FILE_DIRECTORY_PATH;

    @Autowired
    public SellerApprovalFormController(SellerApprovalFormService sellerApprovalFormService,
                                        @Value("${sellerGetFormFileDirectoryPath}") String saveFileDirectoryPath) {
        this.sellerApprovalFormService = sellerApprovalFormService;
        this.SAVE_FILE_DIRECTORY_PATH = saveFileDirectoryPath;
        System.out.println("SAVE_FILE_DIRECTORY_PATH: " + SAVE_FILE_DIRECTORY_PATH);
    }

    @GetMapping("getFormAgain")
    public String getFormAgain(Model model) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 사용자 아이디 가져오기
        String userID = userDetails.getUsername();

        // 아이디 값을 모델에 추가하여 Thymeleaf 템플릿으로 전달
        model.addAttribute("userID", userID);

        // userid 값을 사용하여 리스트 가져오기
        List<SellerRoleDTO> getAgain = sellerApprovalFormService.findId(userID);

        model.addAttribute("getAgain", getAgain);

        return "admin/sellerApprovalForm/getFormAgain";
    }

    @GetMapping("outFormAgain")
    public String outFormAgain(Model model) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 사용자 아이디 가져오기
        String userID = userDetails.getUsername();

        // 아이디 값을 모델에 추가하여 Thymeleaf 템플릿으로 전달
        model.addAttribute("userID", userID);

        // userid 값을 사용하여 사유 가져오기
        String reason = sellerApprovalFormService.findReason(userID);

        model.addAttribute("reason", reason);

        return "admin/sellerApprovalForm/outFormAgain";
    }

    @GetMapping("formGetMain")
    public void formGetMain() {
    }

    @GetMapping("formOutMain")
    public void formOutMain() {
    }

    //판매자 권한 회수 알람 보류 확인용
    @GetMapping("outHoldingAlarm")
    public String outHoldingAlarm(Model model) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 사용자 아이디 가져오기
        String userID = userDetails.getUsername();

        // 아이디 값을 모델에 추가하여 Thymeleaf 템플릿으로 전달
        model.addAttribute("userID", userID);

        Integer holding = sellerApprovalFormService.youHolding(userID);
        boolean alert = holding != null && holding == 1;

        Integer success = sellerApprovalFormService.youSuccess(userID);
        boolean alertGo = success != null && success == 1;

        model.addAttribute("alert", alert);
        model.addAttribute("alertGo", alertGo);

        System.out.println("권한 보류 값이야? " + alert);
        System.out.println("권한 성공 값이야? " + alertGo);

        return "admin/sellerApprovalForm/outHoldingAlarm";
    }

    @GetMapping("getForm")
    public String getForm(Model model) {

            // 현재 인증된 사용자 정보 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 사용자 아이디 가져오기
            String userID = userDetails.getUsername();

            // 아이디 값을 모델에 추가하여 Thymeleaf 템플릿으로 전달
            model.addAttribute("userID", userID);

            return "admin/sellerApprovalForm/getForm";
    }

    @GetMapping("outForm")
    public String outForm(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String userID = userDetails.getUsername();

        model.addAttribute("userID", userID);

        return "admin/sellerApprovalForm/outForm";
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
        File directory = new File(SAVE_FILE_DIRECTORY_PATH);

        if (!directory.exists()) {
            directory.mkdirs(); // 디렉토리 생성
        }

        // 출력하여 값 확인
        System.out.println("SAVE_FILE_DIRECTORY_PATH: " + SAVE_FILE_DIRECTORY_PATH);

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

        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();
        paramFileList.add(registFile);
        paramFileList.add(reportFile);
        paramFileList.add(certificateFile);
        paramFileList.add(bankBookFile);

        // 파일 처리 예시
        for (int i = 0; i < paramFileList.size(); i++) {
            MultipartFile paramFile = paramFileList.get(i);
            if (!paramFile.isEmpty()) {
                try {
                    String originFileName = paramFile.getOriginalFilename();
                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
                    String filePath = SAVE_FILE_DIRECTORY_PATH + "/" + savedFileName;
                    File dest = new File(filePath);

                    // 원본 파일 업로드
                    paramFile.transferTo(dest);
                    // 파일 업로드 성공 시 추가적인 처리를 수행할 수 있습니다.
                    System.out.println(originFileName + " 업로드 성공");

                    /* 썸네일 생성 */
                    int width = 300;  // 썸네일 가로 크기
                    int height = 150; // 썸네일 세로 크기

                    // 파일 정보 저장
                    Map<String, String> fileMap = new HashMap<>();
                    fileMap.put("originFileName", originFileName);
                    fileMap.put("savedFileName", savedFileName);
                    fileMap.put("fileType", FileType.values()[i].toString()); // Enum 값을 순서대로 저장

                    // 썸네일 생성
                    BufferedImage originalImage = ImageIO.read(dest);
                    BufferedImage thumbnailImage = Thumbnails.of(originalImage).size(width, height).asBufferedImage();
                    String thumbnailFilePath = SAVE_FILE_DIRECTORY_PATH + "/thumbnail_" + savedFileName;
                    File thumbnailDest = new File(thumbnailFilePath);
                    ImageIO.write(thumbnailImage, ext.substring(1), thumbnailDest);
                    // 썸네일 생성 성공 시 추가적인 처리를 수행할 수 있습니다.
                    System.out.println(originFileName + " 썸네일 생성 성공");

                    // 원본 파일 삭제
                    boolean isDeleted = dest.delete();
                    if (isDeleted) {
                        System.out.println(originFileName + " 원본 파일 삭제 완료!");
                    } else {
                        System.out.println(originFileName + " 원본 파일 삭제 실패!");
                    }

                    fileList.add(fileMap);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("파일 업로드 실패");

                    /* 어떤 종류의 Exception이 발생하더라도 실패 시 파일삭제해야 합니다. */
                    int cnt = 0;
                    for (int j = 0; j < fileList.size(); j++) {
                        Map<String, String> file = fileList.get(j);

                        File deleteFile = new File(SAVE_FILE_DIRECTORY_PATH + "/" + file.get("savedFileName"));
                        boolean isDeleted1 = deleteFile.delete();

                        File deleteThumbnail = new File(SAVE_FILE_DIRECTORY_PATH + "/thumbnail_" + file.get("savedFileName"));
                        boolean isDeleted2 = deleteThumbnail.delete();

                        if (isDeleted1 && isDeleted2) {
                            cnt++;
                        }
                    }

                    if (cnt == fileList.size()) {
                        System.out.println("업로드에 실패한 모든 사진 삭제 완료!");
                    } else {
                        System.out.println("업로드에 실패한 사진 삭제 실패!");
                    }

                    return "redirect:/admin/errorPage";
                }
            }
        }

        int result = sellerApprovalFormService.sellerInsert(requestParams, fileList);

        if (result > 0) {
            return "redirect:/admin/sellerApprovalForm/getForm";
        } else {
            return "redirect:/admin/errorPage";
        }
    }

    enum FileType {
        LICENSE,
        TELECOM,
        SEAL,
        BANK_BOOK
    }

}