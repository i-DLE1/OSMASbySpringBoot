package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProjectCategoryDTO;

import java.util.List;

public interface ProjectCategoryService {

    List<ProjectCategoryDTO> selectByCategoryType(Integer categoryNo);
}
