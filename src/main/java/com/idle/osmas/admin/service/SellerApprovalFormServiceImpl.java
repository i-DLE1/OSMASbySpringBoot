package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.SellerApprovalFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class SellerApprovalFormServiceImpl implements SellerApprovalFormService {

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

        if (result1 > 0 && result2 > 0) {
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

        if (result1 > 0 && result2 > 0) {
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int sellerInsert(Map<String, String> requestParams) {
        String sellerId = requestParams.get("sellerId");
        String registFileName = requestParams.get("registFileName");
        String reportFileName = requestParams.get("reportFileName");
        String certificateFileName = requestParams.get("certificateFileName");
        String bankBookFileName = requestParams.get("bankBookFileName");

        int result1 = sellerApprovalFormMapper.sellerInsert(requestParams);
        int result2 = sellerApprovalFormMapper.sellerInsertReq(sellerId);
        int result3 = sellerApprovalFormMapper.sellerInsertPermission(sellerId);
        int result4 = sellerApprovalFormMapper.sellerInsertFile1(registFileName);
        int result5 = sellerApprovalFormMapper.sellerInsertFile2(reportFileName);
        int result6 = sellerApprovalFormMapper.sellerInsertFile3(certificateFileName);
        int result7 = sellerApprovalFormMapper.sellerInsertFile4(bankBookFileName);

        int result = 0;

        if (result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5 > 0 && result6 > 0 && result7 > 0) {
            result = 1;
        }

        return result;
    }
}