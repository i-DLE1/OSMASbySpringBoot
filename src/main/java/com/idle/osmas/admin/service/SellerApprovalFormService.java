package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.PermissionRoleDTO;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface SellerApprovalFormService {

    int sellerOut(Map<String, String> requestData);

    int sellerOutCancel(String sellerId);

    int sellerInsert(Map<String, String> requestParams, List<Map<String, String>> fileList);

    List<SellerRoleDTO> findId(String userID);

    String findReason(String userID);

    int sellerUpdate(Map<String, String> requestParams, List<Map<String, String>> fileList);

    int sellerInsertCancel(String sellerId);

    Integer checkgetFormHistory(String userID);

    List<SellerRoleDTO> getFormConfirmation(String userID);

    Integer checkoutFormHistory(String userID);

    List<PermissionRoleDTO> sellerReason(String userID);

    Integer holdingNo(String userID);

    Integer holdingOut(String userID);

    Integer sellerGo(String userID);

    Integer sellerOutt(String userID);
}
