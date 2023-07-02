package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.CategoryDTO;

import java.util.List;

public interface ProjectCategoryService {

    List<CategoryDTO> selectByCategoryType(Integer categoryNo);
}
