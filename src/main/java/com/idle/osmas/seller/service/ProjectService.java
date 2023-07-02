package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectDTO;

public interface ProjectService {
    Integer selectTemporaryProjectNoByUserId(String userId);

    boolean existProjectByProjectNo (int projectNo, String userId);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, String userId);

    ProjectDTO selectProjectByProjectNo(int projectNo, String userId);

    int insertTemporaryProject(ProjectDTO project);

    int updateProjectInfo(ProjectDTO project);

    int deleteProjectByProjectNo(int projectNo);

    int updateProjectContent(Integer no, ProjectDTO project);
}
