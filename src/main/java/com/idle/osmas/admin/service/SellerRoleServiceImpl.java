package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.SellerRoleMapper;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerRoleServiceImpl implements SellerRoleService {

    private final SellerRoleMapper sellerRoleMapper;

    //의존성 주입
    public SellerRoleServiceImpl(SellerRoleMapper sellerRoleMapper) {
        this.sellerRoleMapper = sellerRoleMapper;
    }

    @Override
    public List<SellerRoleDTO> selectAllApplyRole() {
        return sellerRoleMapper.selectAllApplyRole();
    }   //권한 코드를 1만 가지고 있는 사람 조회(판매자 권한을 신청한 사람)

    @Override
    public List<SellerRoleDTO> selectAllHoldingRole() {
        return sellerRoleMapper.selectAllHoldingRole();
    }   //권한 코드가 1이고 알람이 null이 아닌 사람 조회

    public List<SellerRoleDTO> sellerAllRole() {
        return sellerRoleMapper.sellerAllRole();
    } //권한 코드 1과 2를 모두 가지고 있는 사람 조회(모든 판매자)

    @Override
    public List<SellerRoleDTO> selectApplyRoleRetrieve() {
        return sellerRoleMapper.selectApplyRoleRetrieve();
    }   //권한 회수 신청자

    @Override
    public List<SellerRoleDTO> selectHoldingRoleRetrieve() {
        return sellerRoleMapper.selectHoldingRoleRetrieve();
    }   //권한 회수 보류자

    @Override
    public List<SellerRoleDTO> selectSuccessRoleRetrieve() {
        return sellerRoleMapper.selectSuccessRoleRetrieve();
    }   //권한 회수 완료자

}
