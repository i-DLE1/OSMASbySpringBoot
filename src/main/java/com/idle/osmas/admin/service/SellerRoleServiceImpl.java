package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.SellerRoleMapper;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerRoleServiceImpl implements SellerRoleService {

    private final SellerRoleMapper sellerRoleMapper;

    public SellerRoleServiceImpl(SellerRoleMapper sellerRoleMapper) {
        this.sellerRoleMapper = sellerRoleMapper;
    }

    @Override
    public List<SellerRoleDTO> selectAllsellerRole() {
        return sellerRoleMapper.selectAllRole();
    }

//    public List<SellerRoleDTO> selectAllSellerRole() {
//        return sellerRoleMapper.selectAllsellerRole();
//    }

}
