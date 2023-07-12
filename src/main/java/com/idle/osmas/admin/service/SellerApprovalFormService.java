package com.idle.osmas.admin.service;

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

    Integer youHolding(String userID);

    Integer youSuccess(String userID);

    Integer youSeller(String userID);

    int sellerUpdate(Map<String, String> requestParams, List<Map<String, String>> fileList);

    int sellerInsertCancel(String sellerId);
}
