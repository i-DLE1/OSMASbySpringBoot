package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectMapper;
import com.idle.osmas.seller.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectMapper projectMapper;
    private int result;

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
    public int insertTemporaryProject(ProjectDTO project) {
        return projectMapper.insertTemporaryProject(project);
    }

    @Override
    public int updateProjectInfo(ProjectDTO project) {
        return projectMapper.updateProjectInfo(project);
    }

    @Override
    public int deleteProjectByProjectNo(int projectNo) {
        return 0;
    }

    @Override
    public int updateProjectContent(Integer no,ProjectDTO project) {
        return projectMapper.updateProjectContent(no, project.getContent());
    }
}
