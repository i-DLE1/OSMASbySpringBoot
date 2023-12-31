package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.QnaDTO;
import com.idle.osmas.seller.dto.SalesDTO;
import com.idle.osmas.seller.dto.SponsoredPRJDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderListMapper {

    List<SalesDTO> selectProjectByUserNo(Map<String, Object> searchCriteria);
    List<SponsoredPRJDTO> selectOrderList(Map<String, Object> searchCriteria);

    int updateShippingTrackInfo(Map<String, Object> checkList);
    int updateDeliveryStatus(Map<String, Object> checkList);
    int exchangeDeliveryStatus(Map<String, Object> exchangeList);
}
