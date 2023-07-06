package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.ItemApprovalMapper;
import com.idle.osmas.admin.dto.ProductDTO;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemApprovalServiceImpl implements ItemApprovalService{

    private final ItemApprovalMapper itemApprovalMapper;

    public ItemApprovalServiceImpl(ItemApprovalMapper itemApprovalMapper) {
        this.itemApprovalMapper = itemApprovalMapper;
    }

    @Override
    public List<ProductDTO> waitingAllItem() {
        return itemApprovalMapper.waitingAllItem();
    }

    @Override
    public List<ProductDTO> holdingAllItem() {
        return itemApprovalMapper.holdingAllItem();
    }

    @Override
    public List<ProductDTO> successAllItem() {
        return itemApprovalMapper.successAllItem();
    }

}
