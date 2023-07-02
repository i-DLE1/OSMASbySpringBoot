package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Mapper
public interface RegistProjectMapper {

    boolean existProjectByProjectNo (int projectNo, String userId);

    List<CategoryDTO> selectByCategoryType(Integer categoryNo);

    Integer selectTemporaryProjectNoByUserId(String userId);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, String userId);

    List<ProductDTO> selectProductListByProjectNo(int projectNo, String userId);

    List<ProjectFileDTO> selectProjectFileListByProjectNo(int projectNo, String userId);

    int selectMemberNoById(String userId);

    int insertProject(ProjectDTO projectDTO);

    int insertTemporaryProject(ProjectDTO projectDTO);

    int insertProjectProgress(ProjectDTO projectDTO);

    int insertProjectProduct(ProductDTO product);

    int insertProjectProductList(int projectNo , int productNo);

    int updateProjectProduct(ProductDTO product);

    int deleteProjectProduct(int productNo);

    int deleteProjectProductList(int productNo);

    int insertProjectFile(ProjectFileType fileType, String originFile,
                          String savedFile, String deleteYN,
                          int projectNo);

    ProjectFileDTO selectByProjectSaveFileName(String saveFileName, int projectNo);


    int updateProjectContent(int no, String content);

    int insertProjectFAQ(int projectRefNo, ProjectFAQDTO projectFaq);
    int updateProjectFAQ(ProjectFAQDTO projectFAQ);

    int deleteProjectFAQ(int no);

    List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, String userId);

    List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo, String userId);
    ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no, String userId);

    int insertProjectNews(int projectNo, ProjectNewsDTO projectNews);

    int deleteProjectNews(int projectNewsNo);

    int updateProjectNews(ProjectNewsDTO projectNews);

    ProjectDTO selectProjectByProjectNo(int projectNo, String userId);
}
