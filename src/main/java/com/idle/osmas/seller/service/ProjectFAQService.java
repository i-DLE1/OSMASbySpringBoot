package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectFAQDTO;

import java.util.List;

public interface ProjectFAQService {

    int insertProjectFAQ(List<ProjectFAQDTO> projectFAQList);

    int updateProjectFAQ(List<ProjectFAQDTO> projectFAQList);

    int deleteProjectFAQ(List<ProjectFAQDTO> projectFAQList);

    List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, int userNo);

    int deleteProjectFaqByProjectNo(int projectNo);
}
