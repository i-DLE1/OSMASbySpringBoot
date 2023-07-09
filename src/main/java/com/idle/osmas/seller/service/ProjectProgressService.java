package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectProgressDTO;
import com.idle.osmas.seller.dto.ProjectProgressStatus;

public interface ProjectProgressService {

    ProjectProgressDTO progressLastStatusById(int projectNo, ProjectProgressStatus status);

    int insertProjectProgressStatus(ProjectProgressDTO projectProgress);

    int deleteProjectProgressByProjectNo(int projectNo);
}
