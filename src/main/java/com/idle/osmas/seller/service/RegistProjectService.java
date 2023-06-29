package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistProjectService {

    List<CategoryDTO> selectByCategoryType(Integer categoryNo);

    int temporaryInsertProject(ProjectDTO projectDTO);


}
