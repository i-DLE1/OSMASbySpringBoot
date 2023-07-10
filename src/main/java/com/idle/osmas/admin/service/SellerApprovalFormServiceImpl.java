package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.SellerApprovalFormMapper;
import com.idle.osmas.admin.dto.PermissionRoleDTO;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
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
    public int sellerInsert(Map<String, String> requestParams, List<Map<String, String>> fileList) {
        String sellerId = requestParams.get("sellerId");

        int result1 = sellerApprovalFormMapper.sellerInsert(requestParams);
        int result2 = sellerApprovalFormMapper.sellerInsertReq(sellerId);
        int result3 = sellerApprovalFormMapper.sellerInsertPermission(sellerId);

        int result4 = sellerApprovalFormMapper.sellerInsertFileList(fileList, sellerId);

        int result = 0;
        if (result1 > 0 && result2 > 0 && result3 > 0 && result4 == fileList.size()) {
            result = 1;
        }

        return result;
    }

    @Override
    public List<SellerRoleDTO> findId(String userID) {
        return sellerApprovalFormMapper.getFormAgain(userID);
    }

    @Override
    public String findReason(String userID) {
        PermissionRoleDTO permissionRoleDTO = sellerApprovalFormMapper.findReason(userID);
        return permissionRoleDTO != null ? permissionRoleDTO.getRejectReason() : null;
    }
}