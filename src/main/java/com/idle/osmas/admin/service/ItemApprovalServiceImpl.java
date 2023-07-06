package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.ItemApprovalMapper;
import com.idle.osmas.admin.dto.ProductDTO;
import com.idle.osmas.admin.dto.SellerRoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //권한 신청 -> 완료
    @Override
    @Transactional
    public int endProgress(String sellerId, int projectNo) {
        int result = itemApprovalMapper.itemONGOING(sellerId, projectNo);

        if (result > 0) {
            result = 1;
        }
        return result;
    }

    //권한 신청 -> 보류
    @Override
    @Transactional
    public int noProgress(String sellerId, int projectNo, String reasonText) {
        int result1 = itemApprovalMapper.itemNO1(sellerId, projectNo);
        int result2 = itemApprovalMapper.itemNO2(sellerId, projectNo, reasonText);

        int result = 0;

        if (result1 > 0 && result2 > 0) {
            result = 1;
        }
        return result;
    }


}
