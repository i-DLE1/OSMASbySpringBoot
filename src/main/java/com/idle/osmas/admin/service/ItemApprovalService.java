package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.ProductDTO;

import java.util.List;

public interface ItemApprovalService {
    List<ProductDTO> waitingAllItem();

    List<ProductDTO> holdingAllItem();

    List<ProductDTO> successAllItem();

    int endProgress(String sellerId, int projectNo);

    int noProgress(String sellerId, int projectNo, String reasonText);
}
