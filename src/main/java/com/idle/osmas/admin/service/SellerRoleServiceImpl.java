package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.SellerRoleMapper;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SellerRoleServiceImpl implements SellerRoleService {

    private final SellerRoleMapper sellerRoleMapper;

    //의존성 주입
    public SellerRoleServiceImpl(SellerRoleMapper sellerRoleMapper) {
        this.sellerRoleMapper = sellerRoleMapper;
    }

    //권한 신청자
    @Override
    public List<SellerRoleDTO> selectAllApplyRole() {
        return sellerRoleMapper.selectAllApplyRole();
    }

    //권한 보류자
    @Override
    public List<SellerRoleDTO> selectAllHoldingRole() {
        return sellerRoleMapper.selectAllHoldingRole();
    }

    //권한 완료자(모든 판매자)
    public List<SellerRoleDTO> sellerAllRole() {
        return sellerRoleMapper.sellerAllRole();
    }

    //권한 회수 신청자
    @Override
    public List<SellerRoleDTO> selectApplyRoleRetrieve() {
        return sellerRoleMapper.selectApplyRoleRetrieve();
    }

    //권한 회수 보류자
    @Override
    public List<SellerRoleDTO> selectHoldingRoleRetrieve() {
        return sellerRoleMapper.selectHoldingRoleRetrieve();
    }

    //권한 회수 완료자
    @Override
    public List<SellerRoleDTO> selectSuccessRoleRetrieve() {
        return sellerRoleMapper.selectSuccessRoleRetrieve();
    }

    //권한 신청 -> 완료
    @Override
    @Transactional
    public int grant(String sellerId) {
        int result1 = sellerRoleMapper.addRoleToSeller(sellerId);
        System.out.println("result1 : " + result1);
        int result2 =sellerRoleMapper.changeSellerRoleState(sellerId);
        System.out.println("result2 : " + result2);

        int result = 0;

        // sellerRoleMapper의 작업 수행 후 결과를 result 변수에 할당
        if ((result1 > 0) && (result2 > 0)) {
            result = 1;
        }
        return result;
    }

    //권한 회수 신청 -> 완료
    @Override
    @Transactional
    public int drop(String sellerId) {
        int result1 = sellerRoleMapper.addRoleToSellerDrop(sellerId);
        System.out.println("result1 : " + result1);
        int result2 =sellerRoleMapper.changeSellerRoleDropState(sellerId);
        System.out.println("result2 : " + result2);

        int result = 0;

        // sellerRoleMapper의 작업 수행 후 결과를 result 변수에 할당
        if ((result1 > 0) && (result2 > 0)) {
            result = 1;
        }
        return result;
    }

    //권한 신청 -> 보류
    @Override
    @Transactional
    public int holdingGrant(String sellerId, String reason, int sellerReq) {
        int result1 = sellerRoleMapper.addRoleToSellerReason(sellerId, reason, sellerReq);
        System.out.println("result1 : " + result1);
        int result2 =sellerRoleMapper.changeSellerRoleState2(sellerReq);
        System.out.println("result2 : " + result2);

        int result = 0;

        // sellerRoleMapper의 작업 수행 후 결과를 result 변수에 할당
        if ((result1 > 0) && (result2 > 0)) {
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int holdingRetrieveGo(String sellerId, String reason, int sellerReq) {
        int result1 = sellerRoleMapper.addRoleToSellerReasonRetrieve(sellerId, reason, sellerReq);
        System.out.println("result1 : " + result1);
        int result2 =sellerRoleMapper.changeSellerRoleState3(sellerReq);
        System.out.println("result2 : " + result2);

        int result = 0;

        // sellerRoleMapper의 작업 수행 후 결과를 result 변수에 할당
        if ((result1 > 0) && (result2 > 0)) {
            result = 1;
        }
        return result;
    }
}
