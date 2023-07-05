package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProjectCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectCategoryMapper {

    List<ProjectCategoryDTO> selectByCategoryType(Integer categoryNo);

}
