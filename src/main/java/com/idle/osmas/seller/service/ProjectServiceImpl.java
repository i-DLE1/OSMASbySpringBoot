package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectMapper;
import com.idle.osmas.seller.dto.ProductStatistics;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectWishDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public Integer selectTemporaryProjectNoByUserId(int userNo) {
        return projectMapper.selectTemporaryProjectNoByUserId(userNo);
    }

    @Override
    public boolean existProjectByProjectNo(int projectNo, int userNo) {
        Integer result = projectMapper.existProjectByProjectNo(projectNo, userNo);
        if (result == null) return false;
        return true;
    }

    @Override
    public ProjectDTO selectProjectInfoByProjectNo(int projectNo, Integer userNo) {
        return projectMapper.selectProjectInfoByProjectNo(projectNo, userNo);
    }

    @Override
    public ProjectDTO selectProjectByProjectNo(int projectNo, int userNo) {
        return projectMapper.selectProjectByProjectNo(projectNo, userNo);
    }

    @Override
    public List<ProjectDTO> selectByProgressAndSearchProjectManagement(Map<String, Object> searchCriteria) {
        return projectMapper.selectByProgressAndSearchProjectManagement(searchCriteria);
    }

    @Override
    public int selectByProgressAndSearchProjectManagementCount(Map<String, Object> searchCriteria) {
        return projectMapper.selectByProgressAndSearchProjectManagementCount(searchCriteria);
    }

    /**
     *
     * @param params keyList(type) = [categoryNo(int),searchTitle(String), startDate(Date), endDate(Date), views(int) ]
     *
     */
    @Override
    public List<ProjectDTO> selectProjectByCategory(Map<String, Object> params) {

        return projectMapper.selectProjectByCategory(params);
    }

    @Override
    public ProjectDTO selectProjectCancelInfoByProjectId(int projectNo, int userNo) {
        return projectMapper.selectProjectCancelInfoByProjectId(projectNo, userNo);
    }

    @Override
    @Transactional
    public int insertTemporaryProject(ProjectDTO project) {
        return projectMapper.insertTemporaryProject(project);
    }

    @Override
    @Transactional
    public int updateProjectInfo(ProjectDTO project) {
        return projectMapper.updateProjectInfo(project);
    }

    @Override
    @Transactional
    public int deleteProjectByProjectNo(int projectNo) {
        return projectMapper.deleteProjectByProjectNo(projectNo);
    }

    @Override
    @Transactional
    public int updateProjectContent(Integer no,ProjectDTO project) {
        return projectMapper.updateProjectContent(no, project.getContent());
    }


    @Override
    public List<ProjectWishDTO> selectProjectWishByNo(Integer memberNo, Integer projectNo) {
        return projectMapper.selectProjectWishByNo(memberNo, projectNo);
    }

    @Override
    public int insertProjectWish(int memberNo, int projectNo) {
        return projectMapper.insertProjectWish(memberNo, projectNo);
    }

    @Override
    public int deleteProjectWish(int memberNo, int projectNo) {
        return projectMapper.deleteProjectWish(memberNo, projectNo);
    }

    @Override
    public List<ProductStatistics> selectProductStatisticsByProjectNo(int projectNo) {
        return projectMapper.selectProductStatisticsByProjectNo(projectNo);
    }
}
