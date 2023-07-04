package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.SellerApprovalFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class SellerApprovalFormServiceImpl implements SellerApprovalFormService{

    private final SellerApprovalFormMapper sellerApprovalFormMapper;

    public SellerApprovalFormServiceImpl(SellerApprovalFormMapper sellerApprovalFormMapper) {
        this.sellerApprovalFormMapper = sellerApprovalFormMapper;
    }

    @Override
    @Transactional
    public int sellerOut(Map<String, String> requestData) {

        String sellerId = requestData.get("sellerId");

        int result1 = sellerApprovalFormMapper.sellerOutReq(sellerId);
        int result2 = sellerApprovalFormMapper.sellerOut(requestData);

        int result = 0;

        if ((result1 > 0) && (result2 > 0)) {
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int sellerOutCancel(String sellerId) {

        int result1 = sellerApprovalFormMapper.deletePERMISSION(sellerId);
        int result2 = sellerApprovalFormMapper.deleteREQ(sellerId);

        int result = 0;

        if ((result1 > 0) && (result2 > 0)) {
            result = 1;
        }
        return result;
    }

}
