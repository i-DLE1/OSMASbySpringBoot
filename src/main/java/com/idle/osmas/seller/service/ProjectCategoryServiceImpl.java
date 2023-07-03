package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectCategoryMapper;
import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProjectCategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

    private final ProjectCategoryMapper projectCategoryMapper;

    public ProjectCategoryServiceImpl(ProjectCategoryMapper projectCategoryMapper) {
        this.projectCategoryMapper = projectCategoryMapper;
    }

    @Override
    public List<ProjectCategoryDTO> selectByCategoryType(Integer categoryNo) {
        return projectCategoryMapper.selectByCategoryType(categoryNo);
    }
}
