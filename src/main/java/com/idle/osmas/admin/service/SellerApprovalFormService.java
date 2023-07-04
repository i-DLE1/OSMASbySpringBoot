package com.idle.osmas.admin.service;

import java.util.Map;

public interface SellerApprovalFormService {

    int sellerOut(Map<String, String> requestData);

    int sellerOutCancel(String sellerId);

}
