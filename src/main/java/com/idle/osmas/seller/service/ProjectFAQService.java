package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectFAQDTO;

import java.util.List;

public interface ProjectFAQService {

    int insertProjectFAQ(int projectNo, ProjectFAQDTO projectFAQ);

    int updateProjectFAQ(ProjectFAQDTO projectFAQ);

    int deleteProjectFAQ(List<ProjectFAQDTO> projectFAQList);

    List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, String userId);
}
