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

    public List<SellerRoleDTO> sellerAllRole() {
        return sellerRoleMapper.sellerAllRole();
    } //권한 코드 1과 2를 모두 가지고 있는 사람 조회(모든 판매자)

}
