package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectDTO;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    Integer selectTemporaryProjectNoByUserId(int userNo);

    boolean existProjectByProjectNo (int projectNo, int userNo);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, Integer userNo);

    ProjectDTO selectProjectByProjectNo(int projectNo, int userNo);

    List<ProjectDTO> selectProjectByCategory(Map<String, Object> params);

    int insertTemporaryProject(ProjectDTO project);

    int updateProjectInfo(ProjectDTO project);

    int deleteProjectByProjectNo(int projectNo);

    int updateProjectContent(Integer no, ProjectDTO project);
}