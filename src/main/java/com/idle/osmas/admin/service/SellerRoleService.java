package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.SellerRoleDTO;

import java.util.List;

public interface SellerRoleService {

    List<SellerRoleDTO> selectAllsellerRole(); // 회원 번호로 판매자 권한 신청한 모든 회원들의 정보 리스트 조회(권한 코드 1)
}
