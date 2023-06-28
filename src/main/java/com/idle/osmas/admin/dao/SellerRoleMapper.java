package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerRoleMapper {

    List<SellerRoleDTO> selectAllRole(); // 회원 번호로 판매자 권한 신청한 모든 회원들의 정보 리스트 조회(권한 코드 1)

    //List<SellerRoleDTO> selectAllSellerRoleApplicant(int memberNo); // 회원 번호로 모든 판매자 정보 리스트 조회(권한 코드 1, 2)
}