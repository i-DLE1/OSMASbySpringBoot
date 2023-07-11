package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.SponsoredPRJDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderListMapper {
    List<SponsoredPRJDTO> selectOrderList(Integer no);
}
