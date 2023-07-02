package com.idle.osmas.seller.service;

import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.seller.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistProjectService {

    boolean existProjectByProjectNo (int projectNo, String userId);
    Integer selectTemporaryProjectNoByUserId(String userId);

    List<ProductDTO> selectProductListByProjectNo(int projectNo, String userId);

    List<ProjectFileDTO> selectProjectFileListByProjectNo(int projectNo, String userId);
    List<CategoryDTO> selectByCategoryType(Integer categoryNo);

    int selectMemberNoById(String userId);

    int insertProject(ProjectDTO projectDTO);

    int insertTemporaryProject(ProjectDTO projectDTO);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, String userId);

    int insertProjectProduct(List<ProductDTO> productList, String userId, int projectNo);

    int deleteProjectProduct(List<ProductDTO> productList);

    int insertProjectFile( ProjectFileType fileType, String originFile, String savedFile, String deleteYN, int projectNo);

    ProjectFileDTO selectByProjectSaveFileName(String saveFileName, int projectNo);

    int updateProjectContent(ProjectDTO project, Integer no);

    int insertProjectFAQ(int projectNo, ProjectFAQDTO projectFAQ);

    int updateProjectFAQ(ProjectFAQDTO projectFAQ);

    int deleteProjectFAQ(List<ProjectFAQDTO> projectFAQList);

    List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, String userId);

    List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo, String userId);
    ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no, String userId);

    int insertProjectNews(int projectNo, ProjectNewsDTO projectNews);

    int deleteProjectNews(int projectNewsNo);

    int updateProjectNews(ProjectNewsDTO projectNews);

    ProjectDTO selectProjectByProjectNo(int projectNo, String userId);
}
