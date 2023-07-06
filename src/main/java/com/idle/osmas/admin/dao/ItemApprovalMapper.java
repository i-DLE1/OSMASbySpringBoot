package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemApprovalMapper {
    List<ProductDTO> waitingAllItem();

    List<ProductDTO> holdingAllItem();

    List<ProductDTO> successAllItem();
}
