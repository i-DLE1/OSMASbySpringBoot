package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistProjectService {

    int selectTemporaryProjectNoByUserId(String userId);

    List<ProductDTO> selectTemporaryProductListByProjectNo(int projectNo);

    List<ProjectFileDTO> selectTemporaryProjectFileListByProjectNo(int projectNo);
    List<CategoryDTO> selectByCategoryType(Integer categoryNo);

    int temporaryInsertProject(ProjectDTO projectDTO);

    ProjectDTO selectTemporaryProjectInfoByProjectNo(int projectNo);

    int temporaryInsertProjectProduct(List<ProductDTO> productList, String userId);

    int deleteProjectProduct(List<ProductDTO> productList);

    int insertProjectFile( ProjectFileType fileType, String originFile, String savedFile, String deleteYN, int projectNo);

    ProjectFileDTO selectByProjectSaveFileName(String saveFileName, int projectNo);

    int updateProjectContent(ProjectDTO project);

    int insertProjectFAQ(int projectNo ,  ProjectFAQDTO projectFAQ);

    int updateProjectFAQ(ProjectFAQDTO projectFAQ);

    int deleteProjectFAQ(List<ProjectFAQDTO> projectFAQList);

    List<ProjectFAQDTO> selectTemporaryProjectFaqByProjectNo(int projectNo);

    List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo);
    ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no);

    int insertProjectNews(int projectNo, ProjectNewsDTO projectNews);

    int deleteProjectNews(int projectNewsNo);

    int updateProjectNews(ProjectNewsDTO projectNews);
}
