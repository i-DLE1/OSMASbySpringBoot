package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.SellerRoleDTO;

import java.util.List;

public interface SellerRoleService {

    List<SellerRoleDTO> selectAllApplyRole();  //권한 코드를 1만 가지고 있는 사람 조회

    List<SellerRoleDTO> sellerAllRole(); //권한 코드 1과 2를 모두 가지고 있는 사람 조회(모든 판매자)
}
