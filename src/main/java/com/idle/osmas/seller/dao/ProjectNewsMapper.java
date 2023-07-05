package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectNewsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectNewsMapper {

    List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo, String userId);
    ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no, String userId);

    int insertProjectNews(int projectNo, ProjectNewsDTO projectNews);

    int deleteProjectNews(int projectNewsNo);

    int updateProjectNews(ProjectNewsDTO projectNews);

    int deleteProjectFilesByProjectNo(int projectNo);
}
