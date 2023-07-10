package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.SponsoredPRJDTO;

import java.util.List;

public interface OrderListService {

    List<SponsoredPRJDTO> selectOrderList();

}
