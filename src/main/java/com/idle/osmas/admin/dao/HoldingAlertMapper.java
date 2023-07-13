package com.idle.osmas.admin.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HoldingAlertMapper {
    Integer holdingOut(String userID);

    Integer sellerGo(String userID);

    Integer sellerOutt(String userID);

    Integer holdingNo(String userID);
}
