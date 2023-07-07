package com.idle.osmas.admin.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface SellerApprovalFormService {

    int sellerOut(Map<String, String> requestData);

    int sellerOutCancel(String sellerId);

    int sellerInsert(Map<String, String> requestParams, List<Map<String, String>> fileList);

}
