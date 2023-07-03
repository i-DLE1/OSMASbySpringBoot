package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SellerRoleService {

    List<SellerRoleDTO> selectAllApplyRole();  //권한 코드를 1만 가지고 있는 사람 조회

    List<SellerRoleDTO> selectAllHoldingRole(); //권한 코드가 1이고 알람이 null이 아닌 사람 조회

    List<SellerRoleDTO> sellerAllRole(); //권한 코드 1과 2를 모두 가지고 있는 사람 조회(모든 판매자)

    List<SellerRoleDTO> selectApplyRoleRetrieve(); // 권한 회수 신청자

    List<SellerRoleDTO> selectHoldingRoleRetrieve(); // 권한 회수 보류자

    List<SellerRoleDTO> selectSuccessRoleRetrieve(); // 권한 회수 완료자

    int grant(String sellerId); //권한 신청 -> 완료

    int drop(String sellerId); //권한 회수 신청 -> 완료

    int holdingGrant(String sellerId, String reason, int sellerReq);

}

    
