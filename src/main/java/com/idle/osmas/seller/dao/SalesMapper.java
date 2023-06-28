package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.SalesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SalesMapper {

    List<SalesDTO> selectAllProject();
    SalesDTO selectProjectByNo(int no);

}
