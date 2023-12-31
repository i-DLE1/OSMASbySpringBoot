package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProductStatistics;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectWishDTO;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    Integer selectTemporaryProjectNoByUserId(int userNo);

    boolean existProjectByProjectNo (int projectNo, int userNo);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, Integer userNo);

    ProjectDTO selectProjectByProjectNo(int projectNo, int userNo);

    List<ProjectDTO> selectProjectByCategory(Map<String, Object> params);

    List<ProjectDTO> selectByProgressAndSearchProjectManagement(Map<String, Object> searchCriteria);

    int selectByProgressAndSearchProjectManagementCount(Map<String, Object> searchCriteria);

    ProjectDTO selectProjectCancelInfoByProjectId(int projectNo, int userNo);

    int insertTemporaryProject(ProjectDTO project);

    int updateProjectInfo(ProjectDTO project);

    int deleteProjectByProjectNo(int projectNo);

    int updateProjectContent(Integer no, ProjectDTO project);


    List<ProjectWishDTO> selectProjectWishByNo(Integer memberNo, Integer projectNo);

    int insertProjectWish(int memberNo, int projectNo);

    int deleteProjectWish(int memberNo, int projectNo);

    List<ProductStatistics> selectProductStatisticsByProjectNo(int projectNo);

}
