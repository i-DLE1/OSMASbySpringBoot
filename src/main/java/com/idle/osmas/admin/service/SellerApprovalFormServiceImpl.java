package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.HoldingAlertMapper;
import com.idle.osmas.admin.dao.SellerApprovalFormMapper;
import com.idle.osmas.admin.dto.PermissionRoleDTO;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SellerApprovalFormServiceImpl implements SellerApprovalFormService {

    private final SellerApprovalFormMapper sellerApprovalFormMapper;
    private final HoldingAlertMapper holdingAlertMapper;

    public SellerApprovalFormServiceImpl(SellerApprovalFormMapper sellerApprovalFormMapper,
                                         HoldingAlertMapper holdingAlertMapper) {
        this.sellerApprovalFormMapper = sellerApprovalFormMapper;
        this.holdingAlertMapper = holdingAlertMapper;
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
    public int sellerInsertCancel(String sellerId) {

        int result1 = sellerApprovalFormMapper.deleteRole(sellerId);
        int result2 = sellerApprovalFormMapper.deleteFile(sellerId);

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

    @Override
    public Integer youHolding(String userID) {
        Integer result = holdingAlertMapper.youHolding(userID);

        System.out.println("결과값 : " + result);

        if (result != null && result > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public Integer youSuccess(String userID) {
        Integer result = holdingAlertMapper.youSuccess(userID);

        System.out.println("결과값 : " + result);

        if (result != null && result > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public Integer youSeller(String userID) {
        Integer result = holdingAlertMapper.getYouSeller(userID);

        System.out.println("결과값 : " + result);

        if (result != null && result > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public int sellerUpdate(Map<String, String> requestParams, List<Map<String, String>> fileList) {
        String sellerId = requestParams.get("sellerId");

        int result1 = sellerApprovalFormMapper.sellerUpdate(requestParams.get("accountNo"),
                requestParams.get("registNo"),
                requestParams.get("name"),
                requestParams.get("callNumber"),
                requestParams.get("rprsn"),
                requestParams.get("address"),
                requestParams.get("bank"),
                requestParams.get("reportNo"), sellerId);
        int result2 = sellerApprovalFormMapper.sellerInserUpdateReq(sellerId);
        int result3 = sellerApprovalFormMapper.sellerInsertUpdatePermission(sellerId);

        int result4 = sellerApprovalFormMapper.sellerUpdateFileList(fileList, sellerId);

        int result = 0;
        if (result1 > 0 && result2 > 0 && result3 > 0 && result4 == fileList.size()) {
            result = 1;
        }

        return result;
    }

    @Override
    public Integer checkgetFormHistory(String userID) {
        Integer result = sellerApprovalFormMapper.checkgetFormHistory(userID);

        System.out.println("결과값 : " + result);

        if (result != null && result > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public Integer checkoutFormHistory(String userID) {
        Integer result = sellerApprovalFormMapper.checkoutFormHistory(userID);

        System.out.println("결과값 : " + result);

        if (result != null && result > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<PermissionRoleDTO> sellerReason(String userID) {

        List<PermissionRoleDTO> sellerReasonList = sellerApprovalFormMapper.sellerReason(userID);
        System.out.println("값 확인 : " + sellerReasonList);

        return sellerReasonList;
    }

    @Override
    public List<SellerRoleDTO> getFormConfirmation(String userID) {
        return sellerApprovalFormMapper.getFormConfirmation(userID);
    }
}