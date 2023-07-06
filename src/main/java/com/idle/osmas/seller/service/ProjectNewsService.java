package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectNewsDTO;

import java.util.List;

public interface ProjectNewsService {

    List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo, String userId);
    ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no, String userId);

    int insertProjectNews(int projectNo, ProjectNewsDTO projectNews);

    int deleteProjectNews(int projectNewsNo);

    int updateProjectNews(ProjectNewsDTO projectNews);

    int deleteProjectNewsByProjectNo(int projectNo);
}
