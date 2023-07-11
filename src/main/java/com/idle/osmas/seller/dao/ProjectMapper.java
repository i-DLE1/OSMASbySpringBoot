package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProductStatistics;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectWishDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {

    Integer selectTemporaryProjectNoByUserId(int userNo);

    Integer existProjectByProjectNo (int projectNo, int userNo);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, Integer userNo);

    ProjectDTO selectProjectByProjectNo(int projectNo, int userNo);


    List<ProjectDTO> selectProjectByCategory(Map<String, Object> params);

    int selectByProgressAndSearchProjectManagementCount(Map<String, Object> searchCriteria);
    List<ProjectDTO> selectByProgressAndSearchProjectManagement(Map<String, Object> searchCriteria);

    int insertTemporaryProject(ProjectDTO projectDTO);

    int updateProjectInfo(ProjectDTO projectDTO);

    int updateProjectContent(int no, String content);

    int deleteProjectByProjectNo(int projectNo);


    ProjectDTO selectProjectCancelInfoByProjectId(int projectNo, int userNo);

    List<ProjectWishDTO> selectProjectWishByNo(Integer memberNo, Integer projectNo);

    int insertProjectWish(int memberNo, int projectNo);

    int deleteProjectWish(int memberNo, int projectNo);

    List<ProductStatistics> selectProductStatisticsByProjectNo(int projectNo);
}
