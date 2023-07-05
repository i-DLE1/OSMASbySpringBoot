package com.idle.osmas.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

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

    int sellerInsertFile1(String registFileName);

    int sellerInsertFile2(String reportFileName);

    int sellerInsertFile3(String certificateFileName);

    int sellerInsertFile4(String bankBookFileName);
}
