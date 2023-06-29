package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.RegistProjectMapper;
import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegsitProjectServiceImpl implements RegistProjectService {

    private final RegistProjectMapper registProjectMapper;

    public RegsitProjectServiceImpl(RegistProjectMapper registProjectMapper) {
        this.registProjectMapper = registProjectMapper;
    }

    @Override
    public List<CategoryDTO> selectByCategoryType(Integer categoryNo) {
        return registProjectMapper.selectByCategoryType(categoryNo);
    }

    @Override
    public int temporaryInsertProject(ProjectDTO project) {
        int projectResult = registProjectMapper.temporaryInsertProject(project);
        System.out.println("projectResult = " + project);
        int progressResult = registProjectMapper.temporaryInsertProjectProgress(project);
        System.out.println("result = " + project.getNo());

        if (projectResult + progressResult == 2) return 1;
        else return 2;
    }
}
