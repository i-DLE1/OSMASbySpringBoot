package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.OptionDTO;
import com.idle.osmas.seller.dto.SalesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SalesMapper {

    List<SalesDTO> selectAllProject();
    SalesDTO selectProjectByNo(int no);
    List<OptionDTO> selectOptionByNo(int no);
    CategoryDTO selectCategoryByNo(int no);
}
