package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.PermissionRoleDTO;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Mapper
public interface SellerApprovalFormMapper {

    int sellerOutReq(String sellerId);

    int sellerOut(Map<String, String> requestData);

    int deletePERMISSION(String sellerId);

    int deleteREQ(String sellerId);

    int sellerInsert(Map<String, String> requestData);

    int sellerInsertReq(String sellerId);

    int sellerInsertPermission(String sellerId);
    

    List<SellerRoleDTO> getFormAgain(String userID);

    int sellerInsertFileList(List<Map<String, String>> fileList, String sellerId);


    PermissionRoleDTO findReason(String userID);

    int sellerUpdate(String accountNo,String registNo, String name, String callNumber, String rprsn, String address, String bank, String reportNo, String sellerId);

    int sellerInserUpdateReq(String sellerId);

    int sellerInsertUpdatePermission(String sellerId);

    int sellerUpdateFileList(List<Map<String, String>> fileList, String sellerId);

    int deleteRole(String sellerId);

    int deleteFile(String sellerId);
}
