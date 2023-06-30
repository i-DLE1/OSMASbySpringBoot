package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.RegistProjectMapper;
import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ProjectDTO selectTemporaryByUserId(String userId) {

        return registProjectMapper.selectTemporaryByUserId(userId);
    }

    @Override
    public int temporaryInsertProjectProduct(List<ProductDTO> productList, String userId) {
        productList.forEach( e ->{
            if(e.getNo() == 0){
                registProjectMapper.temporaryInsertProjectProduct(e);
                ProjectDTO project =  registProjectMapper.selectTemporaryByUserId(userId);
                registProjectMapper.temporaryInsertProjectProductList(project.getNo(), e.getNo());
            }else {
                registProjectMapper.temporaryUpdateProjectProduct(e);
            }
        });

        return 1;
    }

    @Override
    public int deleteProjectProduct(List<ProductDTO> productList) {
        productList.forEach(e->{
            registProjectMapper.deleteProjectProductList(e.getNo());
            registProjectMapper.deleteProjectProduct(e.getNo());
        });
        return 0;
    }

    @Override
    public int insertProjectFile(ProjectFileType fileType, String originFile, String savedFile, String deleteYN, int projectNo) {

        return registProjectMapper.insertProjectFile(fileType, originFile, savedFile, deleteYN, projectNo);
    }
}
