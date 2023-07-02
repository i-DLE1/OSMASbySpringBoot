package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectCategoryMapper {

    List<CategoryDTO> selectByCategoryType(Integer categoryNo);

}
