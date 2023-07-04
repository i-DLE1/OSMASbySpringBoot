package com.idle.osmas.admin.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SellerApprovalFormMapper {

    int sellerOutReq(String sellerId);

    int sellerOut(Map<String, String> requestData);

    int deletePERMISSION(String sellerId);

    int deleteREQ(String sellerId);
}
