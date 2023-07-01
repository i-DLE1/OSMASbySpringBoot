package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistProjectService {

    List<CategoryDTO> selectByCategoryType(Integer categoryNo);

    int temporaryInsertProject(ProjectDTO projectDTO);

    ProjectDTO selectTemporaryByUserId(String userId);

    int temporaryInsertProjectProduct(List<ProductDTO> productList, String userId);

    int deleteProjectProduct(List<ProductDTO> productList);

    int insertProjectFile( ProjectFileType fileType, String originFile, String savedFile, String deleteYN, int projectNo);
}
