package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.OrderListMapper;
import com.idle.osmas.seller.dto.SponsoredPRJDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderListServiceImpl implements OrderListService{

    private final OrderListMapper orderListMapper;

    public OrderListServiceImpl(OrderListMapper orderListMapper) {
        this.orderListMapper = orderListMapper;
    }


    @Override
    public List<SponsoredPRJDTO> selectOrderList(Integer no) {
        return orderListMapper.selectOrderList(no);
    }
}
