package com.idle.osmas.admin.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HoldingAlertMapper {

    Integer youHolding(String userID);

    Integer youSuccess(String userID);
}

