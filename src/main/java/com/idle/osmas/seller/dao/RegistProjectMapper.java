package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Mapper
public interface RegistProjectMapper {

    int selectMemberNoById(String userId);
}
