package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.SellerRoleDTO;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.MemberRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SellerRoleMapper {

    List<SellerRoleDTO> selectAllApplyRole(); // 회원 번호로 판매자 권한 신청한 모든 회원들의 정보 리스트 조회(권한 코드 1)

    List<SellerRoleDTO> selectAllHoldingRole(); //권한 코드가 1이고 알람이 null이 아닌 사람 조회

    List<SellerRoleDTO> sellerAllRole(); //권한 코드 1과 2를 모두 가지고 있는 사람 조회(모든 판매자)

    List<SellerRoleDTO> selectApplyRoleRetrieve(); // 권한 회수 신청자

    List<SellerRoleDTO> selectHoldingRoleRetrieve(); // 권한 회수 보류자

    List<SellerRoleDTO> selectSuccessRoleRetrieve(); // 권한 회수 완료자

    int addRoleToSeller(String sellerId); //권한 신청 -> 완료

    int changeSellerRoleState(String sellerId); //권한 신청 -> 완료

    int addRoleToSellerDrop(String sellerId); //권한 회수 신청 -> 완료

    int changeSellerRoleDropState(String sellerId); //권한 회수 신청 -> 완료

    int addRoleToSellerReason(String sellerId, String reason, int sellerReq); //권한 신청 -> 보류

    int changeSellerRoleState2(int sellerReq); //권한 신청 -> 보류

    int addRoleToSellerReasonRetrieve(String sellerId, String reason, int sellerReq); //권한 회수 신청 -> 보류

    int changeSellerRoleState3(int sellerReq); //권한 회수 신청 -> 보류


    int insertAlert(String sellerId, String reason, int sellerNo);

    int InsertAlert(String sellerId, int sellerNo);
}