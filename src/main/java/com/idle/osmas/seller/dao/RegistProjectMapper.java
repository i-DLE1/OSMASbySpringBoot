package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Mapper
public interface RegistProjectMapper {

    List<CategoryDTO> selectByCategoryType(@Param("categoryNo") Integer categoryNo);

    Integer selectTemporaryProjectNoByUserId(String userId);

    ProjectDTO selectTemporaryProjectInfoByProjectNo(int projectNo);

    List<ProductDTO> selectTemporaryProductListByProjectNo(int projectNo);

    List<ProjectFileDTO> selectTemporaryProjectFileListByProjectNo(int projectNo);


    int temporaryInsertProject(ProjectDTO projectDTO);

    int temporaryInsertProjectProgress(ProjectDTO projectDTO);

    int temporaryInsertProjectProduct(ProductDTO product);

    int temporaryInsertProjectProductList(@Param("projectNo") int projectNo ,@Param("productNo") int productNo);

    int temporaryUpdateProjectProduct(ProductDTO product);

    int deleteProjectProduct(int productNo);

    int deleteProjectProductList(int productNo);

    int insertProjectFile(@Param("fileType") ProjectFileType fileType,
                          @Param("originFile") String originFile,
                          @Param("savedFile") String savedFile,
                          @Param("deleteYN") String deleteYN,
                          @Param("projectNo") int projectNo);

    ProjectFileDTO selectByProjectSaveFileName(@Param("saveFileName") String saveFileName,
                                               @Param("projectNo") int projectNo);


    int updateProjectContent(@Param("no") int no,
                             @Param("content") String content);

    int insertProjectFAQ(@Param("projectRefNo") int projectRefNo,
                         @Param("projectFaq") ProjectFAQDTO projectFAQ);
    int updateProjectFAQ(ProjectFAQDTO projectFAQ);

    int deleteProjectFAQ(int no);

    List<ProjectFAQDTO> selectTemporaryProjectFaqByProjectNo(int projectNo);

    List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo);
    ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no);

    int insertProjectNews(int projectNo, ProjectNewsDTO projectNews);

    int deleteProjectNews(int projectNewsNo);

    int updateProjectNews(ProjectNewsDTO projectNews);
}
