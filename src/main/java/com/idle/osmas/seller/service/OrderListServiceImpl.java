package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.OrderListMapper;
import com.idle.osmas.seller.dto.QnaDTO;
import com.idle.osmas.seller.dto.SalesDTO;
import com.idle.osmas.seller.dto.SponsoredPRJDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderListServiceImpl implements OrderListService{

    private final OrderListMapper orderListMapper;

    public OrderListServiceImpl(OrderListMapper orderListMapper) {
        this.orderListMapper = orderListMapper;
    }


    @Override
    public List<SalesDTO> selectProjectByUserNo(Map<String, Object> searchCriteria) {
        return orderListMapper.selectProjectByUserNo(searchCriteria);
    }

    @Override
    public List<SponsoredPRJDTO> selectOrderList(Map<String, Object> searchCriteria) {
        return orderListMapper.selectOrderList(searchCriteria);
    }

    @Override
    public int updateShippingTrackInfo(Map<String, Object> checkList) {
        return orderListMapper.updateShippingTrackInfo(checkList);
    }

    @Override
    public int updateDeliveryStatus(Map<String, Object> checkList) {
        return orderListMapper.updateDeliveryStatus(checkList);

    }

    @Override
    public int exchangeDeliveryStatus(Map<String, Object> exchangeList) {
        return orderListMapper.exchangeDeliveryStatus(exchangeList);
    }
}
