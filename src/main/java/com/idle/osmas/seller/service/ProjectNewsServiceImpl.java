package com.idle.osmas.seller.service;


import com.idle.osmas.seller.dao.ProjectNewsMapper;
import com.idle.osmas.seller.dto.ProjectNewsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectNewsServiceImpl implements  ProjectNewsService {

    private final ProjectNewsMapper projectNewsMapper;

    public ProjectNewsServiceImpl(ProjectNewsMapper projectNewsMapper) {
        this.projectNewsMapper = projectNewsMapper;
    }

    @Override
    public List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo, String userId) {
        return projectNewsMapper.selectProjectNewsListByProjectNo(projectNo, userId);
    }

    @Override
    public ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no, String userId) {
        return projectNewsMapper.selectProjectNewsByProjectNewsNo(no, userId);
    }

    @Override
    @Transactional
    public int insertProjectNews(int projectNo, ProjectNewsDTO projectNews) {
        return projectNewsMapper.insertProjectNews(projectNo, projectNews);
    }

    @Override
    @Transactional
    public int deleteProjectNews(int projectNewsNo) {
        return projectNewsMapper.deleteProjectNews(projectNewsNo);
    }

    @Override
    @Transactional
    public int updateProjectNews(ProjectNewsDTO projectNews) {
        return projectNewsMapper.updateProjectNews(projectNews);
    }

    @Override
    @Transactional
    public int deleteProjectNewsByProjectNo(int projectNo) {
        return projectNewsMapper.deleteProjectNewsByProjectNo(projectNo);
    }
}
