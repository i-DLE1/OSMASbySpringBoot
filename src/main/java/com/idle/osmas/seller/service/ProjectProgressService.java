package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectProgressDTO;
import com.idle.osmas.seller.dto.ProjectProgressStatus;
import org.apache.ibatis.annotations.Param;

public interface ProjectProgressService {

    ProjectProgressDTO progressLastStatusById(int projectNo, ProjectProgressStatus status);

    int insertProjectProgressStatus(ProjectProgressDTO projectProgress);

}
